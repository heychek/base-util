package com.github.hckisagoodboy.base.util.common.base;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 洪天才
 * 2018/10/19 3:46 PM
 */
public class PackageUtil {

  /**
   * <p>获取上一层包名</p>
   *
   * @param clazz 需要被获取的包名的类的 <code>Class</code> 对象
   * @return 获取到的上一层包名
   */
  public static String getUpperPackageName(Class<?> clazz) {
    String currPackage = clazz.getPackage().getName();
    int i = currPackage.lastIndexOf(".");
    return i == -1 ? "" : currPackage.substring(0, i);
  }

  /**
   * <p>获取某包下（包括该包的所有子包）所有类</p>
   *
   * @param packageName 包名
   * @return 类的完整名称
   */
  public static List<String> getClassName(String packageName) {
    return getClassName(packageName, false);
  }

  /**
   * <p>获取某包下所有类</p>
   *
   * @param packageName 包名
   * @param childPackage 是否遍历子包
   * @return 类的完整名称
   */
  public static List<String> getClassName(String packageName, boolean childPackage) {
    List<String> fileNames = null;
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    String packagePath = packageName.replace(".", "/");
    URL url = loader.getResource(packagePath);
    if (url != null) {
      String type = url.getProtocol();
      if ("file".equals(type)) {
        fileNames = getClassNameByFile(url.getPath(), null, childPackage);
      } else if ("jar".equals(type)) {
        fileNames = getClassNameByJar(url.getPath(), childPackage);
      }
    } else {
      fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
    }
    return fileNames;
  }

  /**
   * <p>从项目文件获取某包下所有类</p>
   *
   * @param filePath 文件路径
   * @param className 类名集合
   * @param childPackage 是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
    List<String> myClassName = new ArrayList<String>();
    File file = new File(filePath);
    File[] childFiles = file.listFiles();
    for (File childFile : childFiles) {
      if (childFile.isDirectory()) {
        if (childPackage) {
          myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
        }
      } else {
        String childFilePath = childFile.getPath();
        if (childFilePath.endsWith(".class")) {
          childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
          childFilePath = childFilePath.replace("\\", ".");
          myClassName.add(childFilePath);
        }
      }
    }

    return myClassName;
  }

  /**
   * <p>从jar获取某包下所有类</p>
   *
   * @param jarPath jar文件路径
   * @param childPackage 是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
    List<String> myClassName = new ArrayList<String>();
    String[] jarInfo = jarPath.split("!");
    String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
    String packagePath = jarInfo[1].substring(1);
    try {
      JarFile jarFile = new JarFile(jarFilePath);
      Enumeration<JarEntry> entrys = jarFile.entries();
      while (entrys.hasMoreElements()) {
        JarEntry jarEntry = entrys.nextElement();
        String entryName = jarEntry.getName();
        if (entryName.endsWith(".class")) {
          if (childPackage) {
            if (entryName.startsWith(packagePath)) {
              entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
              myClassName.add(entryName);
            }
          } else {
            int index = entryName.lastIndexOf("/");
            String myPackagePath;
            if (index != -1) {
              myPackagePath = entryName.substring(0, index);
            } else {
              myPackagePath = entryName;
            }
            if (myPackagePath.equals(packagePath)) {
              entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
              myClassName.add(entryName);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return myClassName;
  }

  /**
   * <p>从所有jar中搜索该包，并获取该包下所有类</p>
   *
   * @param urls URL集合
   * @param packagePath 包路径
   * @param childPackage 是否遍历子包
   * @return 类的完整名称
   */
  private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
    List<String> myClassName = new ArrayList<String>();
    if (urls != null) {
      for (int i = 0; i < urls.length; i++) {
        URL url = urls[i];
        String urlPath = url.getPath();
        // 不必搜索classes文件夹
        if (urlPath.endsWith("classes/")) {
          continue;
        }
        String jarPath = urlPath + "!/" + packagePath;
        myClassName.addAll(getClassNameByJar(jarPath, childPackage));
      }
    }
    return myClassName;
  }

//  /**
//   * <p>从包package中获取所有的Class</p>
//   *
//   * @param packageName 包名
//   * @param childPackage 子包
//   * @param childClass 子类
//   * @return 获取的所有的Class对象
//   */
//  public static List<Class<?>> getClasses(String packageName, boolean childPackage, boolean childClass){
//
//    //第一个class类的集合
//    List<Class<?>> classes = new ArrayList<Class<?>>();
//    //是否循环迭代
//    boolean recursive = true;
//    //获取包的名字 并进行替换
//    String packageDirName = packageName.replace('.', '/');
//    //定义一个枚举的集合 并进行循环来处理这个目录下的things
//    Enumeration<URL> dirs;
//    try {
//      dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
//      //循环迭代下去
//      while (dirs.hasMoreElements()){
//        //获取下一个元素
//        URL url = dirs.nextElement();
//        //得到协议的名称
//        String protocol = url.getProtocol();
//        //如果是以文件的形式保存在服务器上
//        if ("file".equals(protocol)) {
//          //获取包的物理路径
//          String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
//          //以文件的方式扫描整个包下的文件 并添加到集合中
//          findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes, childPackage, childClass);
//        } else if ("jar".equals(protocol)){
//          //如果是jar包文件
//          //定义一个JarFile
//          JarFile jar;
//          try {
//            //获取jar
//            jar = ((JarURLConnection) url.openConnection()).getJarFile();
//            //从此jar包 得到一个枚举类
//            Enumeration<JarEntry> entries = jar.entries();
//            //同样的进行循环迭代
//            while (entries.hasMoreElements()) {
//              //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
//              JarEntry entry = entries.nextElement();
//              String name = entry.getName();
//              //如果是以/开头的
//              if (name.charAt(0) == '/') {
//                //获取后面的字符串
//                name = name.substring(1);
//              }
//              //如果前半部分和定义的包名相同
//              boolean a = name.startsWith(packageDirName);
//              if (name.startsWith(packageDirName)) {
//                int idx = name.lastIndexOf('/');
//                int len = packageDirName.length();
//                if (idx != packageDirName.length()) {
//                  continue;
//                }
//                //如果以"/"结尾 是一个包
//                if (idx != -1) {
//                  //获取包名 把"/"替换成"."
//                  packageName = name.substring(0, idx).replace('/', '.');
//                }
//                //如果可以迭代下去 并且是一个包
//                if ((idx != -1) && recursive){
//                  //如果是一个.class文件 而且不是目录
//                  if (name.endsWith(".class") && !entry.isDirectory()) {
//                    //去掉后面的".class" 获取真正的类名
//                    String className = name.substring(packageName.length() + 1, name.length() - 6);
//                    try {
//                      //添加到classes
//                      if (childClass) {
//                        classes.add(Class.forName(packageName + '.' + className));
//                      } else {
//                        if (!className.contains("$")) {
//                          classes.add(Class.forName(packageName + '.' + className));
//                        }
//                      }
//                    } catch (ClassNotFoundException e) {
//                      e.printStackTrace();
//                    }
//                  }
//                }
//              }
//            }
//          } catch (IOException e) {
//            e.printStackTrace();
//          }
//        }
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return classes;
//  }

  /**
   * 以文件的形式来获取包下的所有Class
   * @param packageName 包名
   * @param packagePath 包路径
   * @param recursive 是否
   * @param classes class 对象
   * @param childPackage 子包
   * @param childClass 子类
   */
  public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes, boolean childPackage, boolean childClass){
    //获取此包的目录 建立一个File
    File dir = new File(packagePath);
    //如果不存在或者 也不是目录就直接返回
    if (!dir.exists() || !dir.isDirectory()) {
      return;
    }
    //如果存在 就获取包下的所有文件 包括目录
    File[] dirfiles = dir.listFiles(new FileFilter() {
      //自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
      @Override
      public boolean accept(File file) {
        return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
      }
    });
    //循环所有文件
    for (File file : dirfiles) {
      //如果是目录 则继续扫描
      if (file.isDirectory()) {
        if (childPackage) {
          findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
              file.getAbsolutePath(),
              recursive,
              classes,
              childPackage,
              childClass);
        }
      }
      else {
        //如果是java类文件 去掉后面的.class 只留下类名
        String className = file.getName().substring(0, file.getName().length() - 6);
        try {
          //添加到集合中去
          if (childClass) {
            classes.add(Class.forName(packageName + '.' + className));
          } else {
            if (!className.contains("$")) {
              classes.add(Class.forName(packageName + '.' + className));
            }
          }
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      }
    }
  }


}
