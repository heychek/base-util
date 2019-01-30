package com.github.codinghck.base.util.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * <p>corePoolSize: 核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。
 * 除非将allowCoreThreadTimeOut设置为true。
 * <p>maximumPoolSize: 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。
 * 当任务队列为没有设置大小的 LinkedBlockingDeque 时，这个值无效。
 * <p>keepAliveTime: 非核心线程的闲置超时时间，超过这个时间就会被回收。
 * <p>unit: 指定keepAliveTime的单位，如TimeUnit.SECONDS。当将allowCoreThreadTimeOut设置为true时对 corePoolSize 生效。
 * <p>workQueue: 线程池中的任务队列.常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
 * LinkedBlockingQueue 构造的时候若没有指定大小，则默认大小为 Integer.MAX_VALUE，当然也可以在构造函数的参数中指定大小。
 * 但 LinkedBlockingQueue 不接受 null。
 * <p>threadFactory: 线程工厂，提供创建新线程的功能。ThreadFactory 是一个接口，只有一个方法
 * <code>
 *   public interface ThreadFactory {
 *     Thread newThread(Runnable r);
 *   }
 * </code>
 * 通过线程工厂可以对线程的一些属性进行定制。
 * 
 * @author hck
 * 2018/11/13 5:17 PM
 */
public class DefaultThreadPool {
  private static final int DEFAULT_CORE_POOL_SIZE = 5;
  private static final int DEFAULT_MAXIMUM_POOL_SIZE = 5;
  private static final int DEFAULT_KEEP_ALIVE_TIME = 0;
  private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

  public static final ExecutorService defaultExecutorService = new ThreadPoolExecutor(
      DEFAULT_CORE_POOL_SIZE,
      DEFAULT_MAXIMUM_POOL_SIZE,
      DEFAULT_KEEP_ALIVE_TIME,
      DEFAULT_TIME_UNIT,
      new LinkedBlockingQueue<>(),
      new DefaultThreadFactory());

  static class DefaultThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DefaultThreadFactory() {
      SecurityManager s = System.getSecurityManager();
      group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
      namePrefix =  StringUtils.join("pool-", POOL_NUMBER.getAndIncrement(), "-thread-");
    }

    @Override
    public Thread newThread(@NotNull Runnable r) {
      Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(),0L);
      if (t.isDaemon()) {
        t.setDaemon(false);
      }
      if (t.getPriority() != Thread.NORM_PRIORITY) {
        t.setPriority(Thread.NORM_PRIORITY);
      }
      return t;
    }
  }
}
