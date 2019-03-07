package com.github.codinghck.base.util.common.thread;

import com.github.codinghck.base.util.common.lambda.NoParamExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hck 2019-03-06 22:21
 */
@Slf4j
public class NoParamVoidRetryThread extends Thread {

  private long timeout;
  private NoParamExecutor executor;

  public NoParamVoidRetryThread(NoParamExecutor executor, long timeout) {
    setName("retry-thread");
    this.timeout = timeout;
    this.executor = executor;
  }

  @Override
  public void run() {
    try {
      Thread.sleep(timeout);
      executor.execute();
    } catch (InterruptedException e) {
      log.error("发生错误, 重试线程失败");
    }
  }
}
