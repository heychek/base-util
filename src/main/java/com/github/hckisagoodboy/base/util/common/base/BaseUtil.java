package com.github.hckisagoodboy.base.util.common.base;

/**
 * @author 洪天才
 * 2018/10/19 3:46 PM
 */
public class BaseUtil {
//
//  public static boolean isStrNull(String str) {
//    return null == str || "".equals(str.trim());
//  }
//
//  public static boolean isObjNull(Object obj) {
//    return null == obj || "{}".equals(JSONObject.toJSONString(obj)) || "".equals(obj.toString());
//  }
//
//  public static boolean isNullObject(Object obj) {
//    if (obj == null) {
//      return true;
//    }
//
//    if (obj instanceof CharSequence) {
//      return ((CharSequence) obj).length() == 0;
//    }
//    if (obj instanceof Collection) {
//      return ((Collection) obj).isEmpty();
//    }
//    if (obj instanceof Map) {
//      return ((Map) obj).isEmpty();
//    }
//    if (obj instanceof Object[]) {
//      Object[] object = (Object[]) obj;
//      if (object.length == 0) {
//        return true;
//      }
//      boolean empty = true;
//      for (Object anObject : object) {
//        if (!isNullObject(anObject)) {
//          empty = false;
//          break;
//        }
//      }
//      return empty;
//    }
//    return false;
//  }
//
//  public static boolean isStrOutLength(String str, int max) {
//    if (isStrNull(str)) {
//      return false;
//    }
//    return str.length() > max;
//  }
//
//  public static boolean isStrBelowLength(String str, int min) {
//    if (isStrNull(str)) {
//      return false;
//    }
//    return str.length() <= min;
//  }
//
//  public static boolean isListOutLength(List list, int max) {
//    if (isListNull(list)) {
//      return false;
//    }
//    return list.size() > max;
//  }
//
//  public static boolean isListBelowLength(List list, int min) {
//    if (isListNull(list)) {
//      return false;
//    }
//    return list.size() <= min;
//  }
//
//  public static boolean strsContainsNull(String... args) {
//    for (String arg : args) {
//      if (isStrNull(arg)) {
//        return true;
//      }
//    }
//    return false;
//  }
//
//  public static boolean isListNull(List list) {
//    return null == list || list.size() == 0 || "[]".equals(list.toString());
//  }
//
//  public static Date timeMillisToDate(String millis) {
//    Date date = new Date();
//    date.setTime(Long.valueOf(millis));
//    return date;
//  }
//
//  public static String gmtToNorMalDate(Date gmt) {
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Const.NORMAL_DATE_FMT);
//    return simpleDateFormat.format(gmt);
//  }
//
//  public static String listToJsonStr(List list) {
//    StringBuilder sb = new StringBuilder();
//    sb.append("[");
//    for (int i = 0; i < list.size(); i++) {
//      sb.append("\"");
//      sb.append(list.get(i));
//      sb.append("\"");
//      if (i != list.size() - 1) {
//        sb.append(",");
//      }
//    }
//    sb.append("]");
//    return sb.toString();
//  }
//
//  public static String getCurrTime() {
//    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//  }
//
//  public static Object getFieldValueByName(String fieldName, Object o) {
//    try {
//      String firstLetter = fieldName.substring(0, 1).toUpperCase();
//      String getter = "get" + firstLetter + fieldName.substring(1);
//      Method method = o.getClass().getMethod(getter);
//      return method.invoke(o);
//    } catch (Exception e) {
//      return null;
//    }
//  }
//
//  private static boolean isArrNull(Object[] arr) {
//    return null == arr || arr.length == 0;
//  }
//
//  public static int findStrInArr(String[] arr, String target) {
//    if (isArrNull(arr)) {
//      return -1;
//    }
//    for (int i = 0, len = arr.length; i < len; i++) {
//      if (target.equals(arr[i])) {
//        return i;
//      }
//    }
//    return -1;
//  }
//
//  /**
//   * 得到指定数量的UUID，以数组的形式返回
//   */
//  public static String[] getUUID(int num){
//    if( num <= 0) {
//      return null;
//    }
//    String[] uuidArr = new String[num];
//    for (int i = 0; i < uuidArr.length; i++) {
//      uuidArr[i] = getUUID32();
//    }
//    return uuidArr;
//  }
//
//  /**
//   * 得到32位的uuid
//   */
//  public static String getUUID32(){
//    return UUID.randomUUID().toString().replace("-", "").toLowerCase();
//  }
//
//  public static boolean isCarNo(String carNo) {
//    Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽" +
//        "贵粤青藏川宁琼使领 A-Z][A-Z](?:(?![A-Z]{4})[A-Z0-9]){4}[A-Z0-9 挂学警港澳]$");
//    Matcher m = p.matcher(carNo);
//    return m.matches();
//  }
//
//  public static <T, E> T map(DozerBeanMapper mapper, E source, Class<T> clazz) {
//    return null == source ? null : mapper.map(source, clazz);
//  }
}
