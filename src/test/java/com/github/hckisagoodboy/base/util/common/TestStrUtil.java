package com.github.hckisagoodboy.base.util.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hck
 * @date 2018/11/2 1:37 PM
 */
public class TestStrUtil {

  public static void main(String[] args) {
    List<Dog> list = new ArrayList<>();
    for (int i = 0; i < 100000; i++) {
      list.add(new Dog(i, "dog" + i));
    }
    long nanoTime = System.nanoTime();
    test1(list);
    long nanoTime1 = System.nanoTime();
    test2(list);
    long nanoTime2 = System.nanoTime();
    test3(list);
    long nanoTime3 = System.nanoTime();
    test4(list);
    long nanoTime4 = System.nanoTime();
    test5(list);
    long nanoTime5 = System.nanoTime();
    test6(list);
    long nanoTime6 = System.nanoTime();
    System.out.println((nanoTime1-nanoTime)/1000000.0);
    System.out.println((nanoTime2-nanoTime1)/1000000.0);
    System.out.println((nanoTime3-nanoTime2)/1000000.0);
    System.out.println((nanoTime4-nanoTime3)/1000000.0);
    System.out.println((nanoTime5-nanoTime4)/1000000.0);
    System.out.println((nanoTime6-nanoTime5)/1000000.0);
  }

  public static void test1(List<Dog> list) {
    for (int i = 0; i < list.size(); i++) {
      list.get(i).hashCode();
    }
  }

  public static void test2(List<Dog> list) {
    for (int i = 0, len = list.size(); i < len; i++) {
      list.get(i).hashCode();
    }
  }

  public static void test3(List<Dog> list) {
    Iterator<Dog> iterator = list.iterator();
    while (iterator.hasNext()) {
      iterator.next().hashCode();
    }
  }

  public static void test4(List<Dog> list) {
    for (Dog dog : list) {
      dog.hashCode();
    }
  }

  public static void test5(List<Dog> list) {
    list.forEach(Dog::hashCode);
  }

  public static void test6(List<Dog> list) {
    list.iterator().forEachRemaining(Dog::hashCode);
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class Dog {
    private int age;
    private String name;
  }
}
