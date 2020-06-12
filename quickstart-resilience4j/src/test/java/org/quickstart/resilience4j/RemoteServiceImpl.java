package org.quickstart.resilience4j;

import java.util.concurrent.TimeUnit;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/11/16 09:51
 */
public class RemoteServiceImpl implements RemoteService {

  @Override
  public int process(int i) {
    return i;
  }

  @Override
  public String doSomethingWithArgs(String s) {
    return s;
  }

  @Override
  public String doSomethingThrowException() {
    throw new RuntimeException("testException");
  }

  @Override
  public String doSomethingSlowly() {

    try {
      TimeUnit.SECONDS.sleep(30);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public String doSomething() {
    return null;
  }

}
