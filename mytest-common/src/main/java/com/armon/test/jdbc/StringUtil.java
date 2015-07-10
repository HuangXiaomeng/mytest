package com.armon.test.jdbc;

import java.util.regex.Pattern;

public class StringUtil {
  public static String[] splitAddEmptyStr(String str, String regex) {
    return str.split(regex, Integer.MAX_VALUE);
  }

  /**
   * 判断是否为整数
   *
   * @param str
   *            传入的字符串
   *
   * @return 是整数返回true,否则返回false
   */
  public static boolean isInteger(String str) {
      Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
      return pattern.matcher(str).matches();
  }

  /**
   * 检查字符串是否是空白：<code>null</code>、空字符串<code>""</code>或只有空白字符。
   *
   * <pre>
   * StringUtil.isBlank(null)      = true
   * StringUtil.isBlank("")        = true
   * StringUtil.isBlank(" ")       = true
   * StringUtil.isBlank("bob")     = false
   * StringUtil.isBlank("  bob  ") = false
   * </pre>
   *
   * @param str
   *            要检查的字符串
   *
   * @return 如果为空白, 则返回<code>true</code>
   */
  public static boolean isBlank(String str) {
      int length;
      if ((str == null) || ((length = str.length()) == 0)) {
          return true;
      }
      for (int i = 0; i < length; i++) {
          if (!Character.isWhitespace(str.charAt(i))) {
              return false;
          }
      }
      return true;
  }
}
