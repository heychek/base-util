package com.github.codinghck.base.util.common.base.prop;

import com.github.codinghck.base.util.common.base.collection.IteratorUtils;
import java.util.Iterator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author hck 2019-01-30 22:39
 */
@SuppressWarnings({ "unused", "WeakerAccess" })
public class PropSetUtils {

  private PropSetUtils() {}

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
    PropertiesConfiguration props = PropLoadUtils.load(path);
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
}
