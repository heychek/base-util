package com.github.codinghck.base.util.common.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author hck
 * 2018/11/2 3:19 PM
 */
public class PropertiesUtils {

  private PropertiesUtils() {}
  private static final int DEFAULT_INITIAL = 32;
  private static final String DEFAULT_ENCODE = "UTF-8";

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
   * <p>根据 {@code path} 和 指定位置 {@code idx} 获取该文件 {@code idx} 位置处的值</p>
   *
   * @param path 相对于根目录的文件路径
   * @param idx 指定位置, 即第几行
   * @return 该文件 {@code idx} 位置处的值
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static String getValueByPosition(String path, int idx) throws ConfigurationException {
    PropertiesConfiguration props = load(path);
    Iterator<String> it = props.getKeys();
    int currCount = 0;
    while (it.hasNext()) {
      if (idx == ++currCount) {
        return props.getString(it.next());
      }
    }
    return null;
  }

  /**
   * <p>根据 {@code path} 获取该文件第一行配置的值</p>
   *
   * @param path 相对于根目录的文件路径
   * @return 该文件第一行配置的值
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static String getFirstValue(String path) throws ConfigurationException {
    return getValueByPosition(path, 1);
  }

  /**
   * <p>根据 {@code path} 和 指定位置 {@code idx} 设置该文件 {@code idx} 位置处的值为 {@code value}</p>
   *
   * @param path 相对于根目录的文件路径
   * @param idx 指定位置, 即第几行
   * @param value 需要被设置的值
   * @throws ConfigurationException 找不到该文件时抛出异常
   * @throws IndexOutOfBoundsException 指定位置小于 0 或其数值大于该配置文件配置条数, 则抛出该异常
   */
  public static void setValueByPosition(String path, int idx, String value)
      throws ConfigurationException {
    PropertiesConfiguration props = load(path);
    props.setAutoSave(true);
    Iterator<String> it = props.getKeys();
    IteratorUtils.moveIteratorPointer(it, idx - 1);
    props.setProperty(it.next(), value);
  }

  /**
   * <p>根据 {@code path} 设置该文件第 1 行配置的值为 {@code value}</p>
   *
   * @param path 相对于根目录的文件路径
   * @param value 需要被设置的值
   * @throws ConfigurationException 找不到该文件时抛出异常
   * @throws IndexOutOfBoundsException 该配置文件配置条数小于 1, 则抛出该异常
   */
  public static void setFirstValue(String path, String value)
      throws ConfigurationException {
    setValueByPosition(path, 1, value);
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
    PropertiesConfiguration conf = load(path, DEFAULT_ENCODE);
    Iterator<String> it = load(path, DEFAULT_ENCODE).getKeys();
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
    return getToMap(path, DEFAULT_INITIAL);
  }
}
