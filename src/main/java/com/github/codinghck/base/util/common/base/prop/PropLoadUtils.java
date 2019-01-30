package com.github.codinghck.base.util.common.base.prop;

import com.github.codinghck.base.util.common.base.str.StrCommonCharset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author hck 2019-01-30 22:34
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class PropLoadUtils {

  private PropLoadUtils() {}

  private static final int DEFAULT_MAP_INITIAL = 16;
  private static final String DEFAULT_ENCODE = StrCommonCharset.UTF_8.val();

  /**
   * <p>根据 {@code path} 以默认的 {@code UTF-8} 编码获取 <code>PropertiesConfiguration</code> 对象</p>
   *
   * @param path 相对于根目录的文件路径
   * @return <code>PropertiesConfiguration</code> 对象
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static PropertiesConfiguration load(String path) throws ConfigurationException {
    return load(path, DEFAULT_ENCODE);
  }

  /**
   * <p>根据 {@code path} 和 指定编码 {@code encode} 获取 <code>PropertiesConfiguration</code> 对象</p>
   *
   * @param path 相对于根目录的文件路径
   * @param encode 文件编码
   * @return PropertiesConfiguration 对象
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static PropertiesConfiguration load(String path, String encode)
      throws ConfigurationException {
    PropertiesConfiguration props = new PropertiesConfiguration(path);
    props.setEncoding(encode);
    return props;
  }

  /**
   * <p>根据文件路径 {@code path} 和预计 Map 大小 {@code initial} 获取该
   * 配置文件, 并以 Map 形式返回键值对结果</p>
   *
   * @param path 该文件路径
   * @param initial 预计的返回的 <code>Map</code> 大小, 即预计该配置文件配置条数
   * @return 以 Map 形式返回的该文件键值对结果
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static Map<String, String> getToMap(String path, int initial)
      throws ConfigurationException {
    Map<String, String> res = new HashMap<>(initial);
    PropertiesConfiguration conf = PropLoadUtils.load(path, DEFAULT_ENCODE);
    Iterator<String> it = PropLoadUtils.load(path, DEFAULT_ENCODE).getKeys();
    while (it.hasNext()) {
      String key = it.next();
      String value = conf.getString(key);
      res.put(key, value);
    }
    return res;
  }

  /**
   * <p>根据文件路径 {@code path} 和默认预计的 <code>Map</code> 大小 获取该
   * 配置文件, 并以 Map 形式返回键值对结果</p>
   *
   * @param path 该文件路径
   * @return 以 <code>Map</code> 形式返回的该文件键值对结果
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static Map<String, String> getToMap(String path) throws ConfigurationException {
    return getToMap(path, DEFAULT_MAP_INITIAL);
  }
}
