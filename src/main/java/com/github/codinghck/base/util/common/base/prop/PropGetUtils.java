package com.github.codinghck.base.util.common.base.prop;

import com.github.codinghck.base.util.common.base.collection.IteratorUtils;
import com.github.codinghck.base.util.common.base.str.StrCommonCharset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author hck
 * 2018/11/2 3:19 PM
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class PropGetUtils {

  private PropGetUtils() {}

  /**
   * <p>根据 {@code path} 和 指定位置 {@code idx} 获取该文件 {@code idx} 位置处的值</p>
   *
   * @param path 相对于根目录的文件路径
   * @param idx 指定位置, 即第几行
   * @return 该文件 {@code idx} 位置处的值
   * @throws ConfigurationException 找不到该文件时抛出异常
   */
  public static String getValueByPosition(String path, int idx) throws ConfigurationException {
    PropertiesConfiguration props = PropLoadUtils.load(path);
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
}
