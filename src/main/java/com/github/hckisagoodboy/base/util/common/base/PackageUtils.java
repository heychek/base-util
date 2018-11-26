package com.github.hckisagoodboy.base.util.common.base;

import com.github.hckisagoodboy.base.util.common.constant.Const;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author hck 2018/11/5 9:22 AM
 */
public class PackageUtils {
  private PackageUtils() {}

  public static final String PACKAGE_SEPARATOR = ".";
  public static final String FILE_PACKAGE_TYPE = "file";
  public static final String JAR_PACKAGE_TYPE = "jar";
  public static final String JAR_PATH_SEPARATOR = "!";
  public static final String CLASS_FILE_POSTFIX = ".class";
  public static final String UNNECESSARY_CLASSES = "classes";

  /**
   * <p>根据包分隔符获取上一层包名</p>
   *
   * @param clazz 需要被获取的包名的类的 <code>Class</code> 对象
   * @return 获取到的上一层包名
   */
  public static String getUpperPackagePackageSeparator(Class<?> clazz) {
    String currPackage = clazz.getPackage().getName();
    return getUpperPackageName(currPackage, PACKAGE_SEPARATOR);
  }

  /**
   * <p>根据文件分隔符获取上一层包名</p>
   *
   * @param clazz 需要被获取的包名的类的 <code>Class</code> 对象
   * @return 获取到的上一层包名
   */
  public static String getUpperPackageFileSeparator(Class<?> clazz) {
    String currPackage = clazz.getPackage().getName();
    return getUpperPackageName(currPackage, File.separator);
  }

  /**
   * <p>根据文件分隔符和包名获取上一层包名</p>
   *
   * @param packageName 需要被获取的包名路径
   * @return 获取到的上一层包名
   */
  public static String getUpperPackageFileSeparator(String packageName) {
    int i = packageName.lastIndexOf(File.separator);
    return i == -1 ? Const.EMPTY_STRING : packageName.substring(0, i);
  }

  /**
   * <p>根据包分隔符和包名获取上一层包名</p>
   *
   * @param packageName 需要被获取的包名路径
   * @return 获取到的上一层包名
   */
  public static String getUpperPackagePackageSeparator(String packageName) {
    int i = packageName.lastIndexOf(PACKAGE_SEPARATOR);
    return i == -1 ? Const.EMPTY_STRING : packageName.substring(0, i);
  }

  /**
   * <p>获取上一层包名</p>
   * <p>getUpperPackageName(String.class) = "java"</p>
   *
   * @param clazz 需要被获取的包名的类的 <code>Class</code> 对象
   * @param separator 分隔符
   * @return 获取到的上一层包名
   */
  public static String getUpperPackageName(Class<?> clazz, String separator) {
    String currPackage = clazz.getPackage().getName();
    return getUpperPackageName(currPackage, separator);
  }

  /**
   * <p>获取上一层包名</p>
   * <p>getUpperPackageName("java.lang") = "java"</p>
   *
   * @param packageName 需要被获取的包名路径
   * @param separator 分隔符
   * @return 获取到的上一层包名
   */
  public static String getUpperPackageName(String packageName, String separator) {
    int i = packageName.lastIndexOf(separator);
    return i == -1 ? Const.EMPTY_STRING : packageName.substring(0, i);
  }

  /**
   * <p>获取某包下（包括该包的所有子包）所有类, 默认不包括子包中的类</p>
   *
   * @param packageName 包名
   * @return 类的完整名称列表
   */
  public static List<String> getClassNamesWithoutChild(String packageName) {
    return getClassNames(packageName, false);
  }

  /**
   * <p>获取某包下（包括该包的所有子包）所有类, 包括子包中的类</p>
   *
   * @param packageName 包名
   * @return 类的完整名称列表
   */
  public static List<String> getClassNamesWithChild(String packageName) {
    return getClassNames(packageName, true);
  }

  /**
   * <p>获取某包下所有类</p>
   *
   * @param packageName 包名
   * @param needChild 是否遍历子包
   * @return 类的完整名称列表
   */
  public static List<String> getClassNames(String packageName, boolean needChild) {
    List<String> fileNames = null;
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    String packagePath = packageName.replace(PACKAGE_SEPARATOR, File.separator);
    URL url = loader.getResource(packagePath);
    if (url != null) {
      String type = url.getProtocol();
      if (FILE_PACKAGE_TYPE.equals(type)) {
        fileNames = getClassNamesByFile(url.getPath(), needChild);
      } else if (JAR_PACKAGE_TYPE.equals(type)) {
        fileNames = getClassNamesByJar(url.getPath(), needChild);
      }
    } else {
      fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, needChild);
    }
    return fileNames;
  }

  /**
   * <p>从项目文件获取某包下所有类</p>
   *
   * @param filePath 文件路径
   * @param needChild 是否遍历子包
   * @return 类的完整名称列表
   */
  public static List<String> getClassNamesByFile(String filePath, boolean needChild) {
    List<String> res = new ArrayList<>();
    File file = new File(filePath);
    File[] childFiles = file.listFiles();
    if (childFiles != null) {
      for (File childFile : childFiles) {
        if (childFile.isDirectory() && needChild) {
          res.addAll(getClassNamesByFile(childFile.getPath(), true));
        }
        if (!childFile.isDirectory()) {
          String childFilePath = getClassNameByFile(childFile);
          if (getClassNameByFile(childFile) != null) {
            res.add(childFilePath);
          }
        }
      }
    }
    return res;
  }

  /**
   * <p>根据 {@code File} 对象获取类名</p>
   *
   * @param file 文件 {@code File} 对象
   * @return 根据该文件 {@code File} 对象获取到的类名, 如果为空, 则返回 {@code noll}
   */
  public static String getClassNameByFile(File file) {
    String filePath = file.getPath();
    if (filePath.endsWith(CLASS_FILE_POSTFIX)) {
      filePath = filePath.substring(
          filePath.indexOf(UNNECESSARY_CLASSES) + UNNECESSARY_CLASSES.length() + 1,
          filePath.lastIndexOf(PACKAGE_SEPARATOR));
      filePath = filePath.replace(File.separator, PACKAGE_SEPARATOR);
      return filePath;
    }
    return null;
  }

  /**
   * <p>从所有 jar 中搜索该包，并获取该包下所有类</p>
   *
   * @param urls URL 集合
   * @param packagePath 包路径
   * @param needChild 是否遍历子包
   * @return 类的完整名称列表
   */
  public static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean needChild) {
    List<String> res = new ArrayList<>();
    if (urls != null) {
      for (URL url : urls) {
        String urlPath = url.getPath();
        if (!urlPath.endsWith(UNNECESSARY_CLASSES + File.separator)) {
          String jarPath = urlPath + JAR_PATH_SEPARATOR + File.separator + packagePath;
          res.addAll(getClassNamesByJar(jarPath, needChild));
        }
      }
    }
    return res;
  }

  /**
   * <p>从 jar 获取某包下所有类</p>
   *
   * @param jarPath jar 文件路径
   * @param needChild 是否遍历子包
   * @return 类的完整名称列表
   */
  public static List<String> getClassNamesByJar(String jarPath, boolean needChild) {
    String[] jarInfo = jarPath.split(JAR_PATH_SEPARATOR);
    String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf(File.separator));
    String packagePath = jarInfo[1].substring(1);
    try {
      return getClassNamesByJar(jarFilePath, packagePath, needChild);
    } catch (Exception ignored) {
      return new ArrayList<>();
    }
  }

  /**
   * <p>从 jar 获取 jar 中某个包路径下的所有类</p>
   *
   * @param jarFilePath jar 文件路径
   * @param packagePath 包路径
   * @param needChild 是否遍历子包
   * @return 类的完整名称列表
   */
  public static List<String> getClassNamesByJar(
      String jarFilePath, String packagePath, boolean needChild) throws IOException {
    List<String> res = new ArrayList<>();
    JarFile jarFile = new JarFile(jarFilePath);
    Enumeration<JarEntry> entries = jarFile.entries();
    while (entries.hasMoreElements()) {
      JarEntry jarEntry = entries.nextElement();
      String entryName = jarEntry.getName();
      String validName = getValidName(packagePath, entryName, needChild);
      if (validName != null) {
        res.add(validName);
      }
    }
    return res;
  }

  /**
   * <p>根据包路径和是否包含子包类的条件, 判断传入的包含路径的类名或编译后的文件名是否合法
   * <p>如果合法则返回完整类名, 否则返回 {@code null}
   *
   * @param packagePath 包路径
   * @param className 包含路径的类名
   * @param needChild 是否包含子包类
   * @return 如果合法则返回完整类名, 否则返回 {@code null}
   */
  public static String getValidName(String packagePath, String className, boolean needChild) {
    if (!className.endsWith(CLASS_FILE_POSTFIX)) {
      return null;
    }
    boolean isChildValid = needChild && className.startsWith(packagePath);
    boolean isNotChildValid =
        !needChild && packagePath.equals(getUpperPackageFileSeparator(className));
    if (!isChildValid && !isNotChildValid) {
      return null;
    }
    return className.replace(File.separator, PACKAGE_SEPARATOR)
        .substring(0, className.lastIndexOf(PACKAGE_SEPARATOR));
  }
}
