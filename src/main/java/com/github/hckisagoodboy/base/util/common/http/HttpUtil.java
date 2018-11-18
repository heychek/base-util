package com.github.hckisagoodboy.base.util.common.http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

/**
 * @author hck
 * 2018/9/28 下午3:59
 */
public class HttpUtil {
  private static final String DEFAULT_CHARSET = "UTF-8";

  /**
   * get请求，参数拼接在地址上
   *
   * @param url 请求地址加参数
   * @return 响应
   */
  public static String get(String url) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(new HttpGet(url));
      if (isResponseOk(response)) {
        return entityToString(response.getEntity());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeHttpClient(httpClient, response);
    }
    return null;
  }

  /**
   * get请求，参数放在map里
   *
   * @param url 请求地址
   * @param map 参数map
   * @return 响应
   */
  public static String getMap(String url, Map<String, String> map) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    try {
      URIBuilder builder = new URIBuilder(url);
      builder.setParameters(getPairs(map));
      HttpGet get = new HttpGet(builder.build());
      response = httpClient.execute(get);
      if (isResponseOk(response)) {
        return entityToString(response.getEntity());
      }
    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();
    } finally {
      closeHttpClient(httpClient, response);
    }
    return null;
  }

  /**
   * 发送post请求，参数用map接收
   *
   * @param url 地址
   * @param map 参数
   * @return 返回值
   */
  public static String postMap(String url, Map<String, String> map) {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    CloseableHttpResponse response = null;
    try {
      post.setEntity(new UrlEncodedFormEntity(getPairs(map), DEFAULT_CHARSET));
      response = httpClient.execute(post);
      if (isResponseOk(response)) {
        return entityToString(response.getEntity());
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeHttpClient(httpClient, response);
    }
    return null;
  }

  /**
   * post请求，参数为json字符串
   *
   * @param url 请求地址
   * @param jsonString json字符串
   * @return 响应
   * @throws IOException 执行失败抛出异常
   */
  public static String postJson(String url, String jsonString) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    post.addHeader("Content-type", "application/json; charset=utf-8");
    post.setHeader("Accept", "application/json");
    CloseableHttpResponse response = null;
    try {
      StringEntity s = new StringEntity(jsonString, Charset.forName("UTF-8"));
      s.setContentType("application/json");
      s.setContentEncoding("UTF-8");
      post.setEntity(s);

      response = httpClient.execute(post);
//      if (isResponseOk(response)) {
//        return entityToString(response.getEntity());
//      }
      return entityToString(response.getEntity());
    } finally {
      closeHttpClient(httpClient, response);
    }
  }

  public static String addMapToUrl(String url, Map<String, String> map) {
    if (StringUtils.hasText(url) || map.isEmpty()) {
      return url;
    }
    String conn = url.contains("?") ? "&" : "?";
    StringBuilder res = new StringBuilder();
    res.append(url);
    for (Map.Entry<String, String> entry : map.entrySet()) {
      res.append(conn);
      res.append(entry.getKey());
      try {
        res.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
      } catch (UnsupportedEncodingException e) {
        res.append(entry.getValue());
      }
      conn = "&";
    }
    return res.toString();
  }

  private static void closeHttpClient(CloseableHttpClient httpClient, CloseableHttpResponse response) {
    try {
      httpClient.close();
      if (response != null) {
        response.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static boolean isResponseOk(CloseableHttpResponse response) {
    return response != null && response.getStatusLine().getStatusCode() == 200;
  }

  private static List<NameValuePair> getPairs(Map<String, String> map) {
    List<NameValuePair> pairs = new ArrayList<>();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }
    return pairs;
  }

  private static String entityToString(HttpEntity entity) throws IOException {
    if (null == entity) {
      return null;
    }
    if (isEntityLenValid(entity)) {
      return EntityUtils.toString(entity, DEFAULT_CHARSET);
    }
    InputStreamReader reader1 = new InputStreamReader(entity.getContent(), DEFAULT_CHARSET);
    CharArrayBuffer buffer = new CharArrayBuffer(2048);
    char[] tmp = new char[1024];
    int l;
    while ((l = reader1.read(tmp)) != -1) {
      buffer.append(tmp, 0, l);
    }
    return buffer.toString();
  }

  private static boolean isEntityLenValid(HttpEntity entity) {
    long len = entity.getContentLength();
    return len != -1 && len < 2048;
  }
}
