package com.github.codinghck.base.util.common.base.str;

import java.nio.charset.Charset;

/**
 * 常见编码枚举
 *
 * @author hck 2019-01-30 17:17
 */
@SuppressWarnings("unused")
public enum StrCommonCharset {
  /**
   * 根据编码名命名
   */
  US_ASCII("US-ASCII"),
  UTF_16("UTF-16"),
  ISO_8859_1("ISO-8859-1"),
  GB2312("GB2312"),
  GB18030("GB18030"),
  GBK("GBK"),
  UTF_16BE("UTF-16BE"),
  UTF_16LE("UTF-16LE"),
  UTF_8("UTF-8");

  private String val;
  private Charset charset;

  StrCommonCharset(String val) {
    this.val = val;
    this.charset = Charset.forName(val);
  }

  public String val() {
    return val;
  }

  public Charset charset() {
    return charset;
  }
}
