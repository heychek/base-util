package com.github.hckisagoodboy.base.util.common.http;

import net.sf.json.JSONObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author hck 2018/11/18 9:53 PM
 */
public class HttpUtils {

  private HttpUtils() {}

  private static final String CONTENT_TYPE = "Content-type";
  private static final String ACCEPT = "Accept";
  private static final String JSON_POST_CONTENT_TYPE = "application/json; charset=utf-8";
  private static final String JSON_POST_ACCEPT = "application/json";
  private static final String DEFAULT_CHARSET = "UTF-8";
  private static final String PARAM_K_V_CONN = "=";
  private static final String PARAM_TO_PARAM_CONN = "&";
  private static final String URL_TO_PARAM_CONN = "?";
  private static final int INVALID_ENTITY_LENGTH = -1;
  private static final int MAX_ENTITY_LENGTH = 2048;
  private static final int SUCCESS_STATUS_CODE = 200;

  /**
   * get请求, 参数放在map里
   *
   * @param url 请求地址
   * @param map 参数map
   * @return {@code String} 对象的响应结果
   * @throws IOException 执行失败抛出异常
   */
  public static String getMap(String url, Map<String, String> map) throws IOException {
    return doGet(addMapToUrl(url, map));
  }

  /**
   * get请求, 参数放在map里
   *
   * @param url 请求地址
   * @param map 参数map
   * @return {@code HttpEntity} 对象的响应结果
   * @throws IOException 执行失败抛出异常
   */
  public static HttpEntity getMapEntityResult(String url, Map<String, String> map)
      throws IOException {
    return doGetEntityResult(addMapToUrl(url, map));
  }

  /**
   * get请求, 参数拼接在地址上
   *
   * @param url 请求地址加参数
   * @return {@code String} 对象的响应结果
   * @throws IOException 执行失败抛出异常
   */
  public static String doGet(String url) throws IOException {
    return entityToString(doGetEntityResult(url));
  }

  /**
   * get请求, 参数拼接在地址上
   *
   * @param url 请求地址加参数
   * @return {@code HttpEntity} 对象的响应结果
   * @throws IOException 执行失败抛出异常
   */
  public static HttpEntity doGetEntityResult(String url) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(new HttpGet(url));
      return response.getEntity();
    } finally {
      closeHttpClient(httpClient);
      closeResponse(response);
    }
  }

  /**
   * <p>发送 post 请求, {@code map} 转换为 body 参数</p>
   *
   * @param url 地址
   * @param map 参数
   * @return 返回值
   * @throws IOException 执行失败抛出异常
   */
  public static String postMap(String url, Map<String, String> map) throws IOException {
    return postJson(url, JSONObject.fromObject(map).toString());
  }

  /**
   * <p>发送 post 请求, {@code body} 参数为 json 字符串</p>
   *
   * @param url 请求地址
   * @param body 用以 post 请求的 json 字符串形式的 {@code body} 参数
   * @return 字符串形式的响应结果
   * @throws IOException 执行失败抛出异常
   */
  public static String postJson(String url, String body) throws IOException {
    return entityToString(postJsonEntityResult(url, body));
  }

  /**
   * <p>发送 post 请求, {@code body} 参数为 json 字符串</p>
   *
   * @param url 请求地址
   * @param body 用以 post 请求的 json 字符串形式的 {@code body} 参数
   * @return {@code HttpEntity} 对象形式的响应结果
   * @throws IOException 执行失败抛出异常
   */
  public static HttpEntity postJsonEntityResult(String url, String body) throws IOException {
    HttpPost post = new HttpPost(url);
    post.addHeader(CONTENT_TYPE, JSON_POST_CONTENT_TYPE);
    post.setHeader(ACCEPT, JSON_POST_ACCEPT);
    post.setEntity(getJsonBodyStringEntity(body));
    return doPost(post);
  }

  /**
   * <p>根据 {@code HttpPost} 对象发送 post 请求</p>
   * @param post {@code HttpPost} 对象
   * @return 请求返回结果的 {@code HttpEntity} 对象
   * @throws IOException 请求发生 IO 异常时抛出
   */
  public static HttpEntity doPost(HttpPost post) throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(post);
      return response.getEntity();
    } finally {
      closeHttpClient(httpClient);
      closeResponse(response);
    }
  }

  /**
   * <p>把用以 {@code json} 请求的 {@code json} 字符串形式的 {@code body} 内容
   * <p>转换为 {@code StringEntity} 对象
   *
   * @param body {@code json} 字符串形式的 {@code body} 参数
   * @return {@code StringEntity} 对象
   */
  public static StringEntity getJsonBodyStringEntity(String body) {
    StringEntity entity = new StringEntity(body, Charset.forName(DEFAULT_CHARSET));
    entity.setContentType(JSON_POST_ACCEPT);
    entity.setContentEncoding(DEFAULT_CHARSET);
    return entity;
  }

  /**
   * <p>把 {@code map} 中的所有参数名和参数值的键值对都添加到 {@code url} 上并返回结果</p>
   *
   * @param url 原 url
   * @param map 参数键值对
   * @return 返回添加后含添加参数的 url, 如果 {@code url} 为 {@code null} 或者为空字符串, 或者 {@code map} 为空的话, 则返回原传入
   * {@code url} 参数
   */
  public static String addMapToUrl(String url, Map<String, String> map) {
    boolean isValid = StringUtils.hasText(url) && map != null && !map.isEmpty();
    if (!isValid) {
      return url;
    }

    StringBuilder res = new StringBuilder(url);
    String conn = url.contains(URL_TO_PARAM_CONN) ? PARAM_TO_PARAM_CONN : URL_TO_PARAM_CONN;
    for (Map.Entry<String, String> entry : map.entrySet()) {
      res.append(conn).append(toParamStr(entry.getKey(), entry.getValue()));
      conn = PARAM_TO_PARAM_CONN;
    }

    return res.toString();
  }

  /**
   * <p>将传入的 {@code key} 和 {@code value} 结合成 {@code key=value} 的 url 参数形式</p>
   * <p>toParamStr("key", "value") = "key=value"</p>
   * <p>toParamStr("page", "3") = "page=3"</p>
   *
   * @param key 参数名
   * @param value 参数值
   * @return 返回 {@code key=value} 的 url 参数形式, 如果传入的参数名为 null 或者空字符串, 或者参数值 为 null, 则抛出异常
   */
  public static String toParamStr(String key, String value) {
    Assert.hasText(key, "key 不能为空");
    Assert.notNull(value, "value 不能为 null");
    return key + PARAM_K_V_CONN + encodeParamByDefaultCharset(value);
  }

  /**
   * <P>按默认编码对字符串进行转码成 URL 格式</P>
   *
   * @param param 传入的参数
   * @return 如果默认格式无法转码, 则返回原字符串, 如传入 param 为 null, 则抛出异常
   */
  public static String encodeParamByDefaultCharset(String param) {
    Assert.notNull(param, "param 不能为 null");
    try {
      return URLEncoder.encode(param, DEFAULT_CHARSET);
    } catch (UnsupportedEncodingException e) {
      return param;
    }
  }

  /**
   * <p>将传入的 <code>HttpEntity</code> 对象转换为字符串</p>
   *
   * @param entity 传入的 <code>HttpEntity</code> 对象
   * @return 如果传入的 <code>HttpEntity</code> 对象为 {@code null} 则返回 {@code null}
   * @throws IOException if an error occurs reading the input stream 则抛出异常
   */
  public static String entityToString(HttpEntity entity) throws IOException {
    if (entity == null) {
      return null;
    }

    if (isEntityLEnOutRange(entity)) {
      return EntityUtils.toString(entity, DEFAULT_CHARSET);
    }

    InputStreamReader reader = new InputStreamReader(entity.getContent(), DEFAULT_CHARSET);
    CharArrayBuffer buffer = new CharArrayBuffer(MAX_ENTITY_LENGTH);
    char[] tmp = new char[1024];
    int l;
    while ((l = reader.read(tmp)) != -1) {
      buffer.append(tmp, 0, l);
    }

    return buffer.toString();
  }

  /**
   * <p>判断传入的 <code>HttpEntity</code> 对象长度是否符合预期</p>
   *
   * @param entity <code>HttpEntity</code> 对象
   * @return 是否有效的布尔值
   */
  public static boolean isEntityLEnOutRange(HttpEntity entity) {
    long len = entity.getContentLength();
    return len != INVALID_ENTITY_LENGTH && len < MAX_ENTITY_LENGTH;
  }

  /**
   * <p>判断 {@code HttpResponse} 对象所代表的结果是否是成功状态</p>
   *
   * @param response 传入的 {@code HttpResponse} 对象
   * @return 返回结果
   */
  public static boolean isResponseSuccess(HttpResponse response) {
    return response != null && response.getStatusLine().getStatusCode() == SUCCESS_STATUS_CODE;
  }

  /**
   * <p>关闭 {@code response} 连接资源</p>
   *
   * @param response {@code CloseableHttpResponse} 对象
   * @throws IOException 关闭遇到 IO 异常时抛出
   */
  public static void closeResponse(CloseableHttpResponse response) throws IOException {
    if (response != null) {
      response.close();
    }
  }

  /**
   * <p>关闭 {@code httpClient} 连接资源</p>
   *
   * @param httpClient {@code CloseableHttpClient} 对象
   * @throws IOException 关闭遇到 IO 异常时抛出
   */
  public static void closeHttpClient(CloseableHttpClient httpClient) throws IOException {
    if (httpClient != null) {
      httpClient.close();
    }
  }

  /**
   * <p>把 {@code map} 中的键值对转换为 {@code NameValuePair} 对象列表</p>
   *
   * @param map 传入的 {@code map} 参数
   * @return {@code NameValuePair} 对象列表
   */
  private static List<NameValuePair> toPairList(Map<String, String> map) {
    List<NameValuePair> pairs = new ArrayList<>();
    for (Map.Entry<String, String> entry : map.entrySet()) {
      pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }

    return pairs;
  }
}
