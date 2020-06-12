package org.quickstart.resilience4j;

/**
 * @author yangzl
 * @description TODO
 * @createTime 2019/11/16 09:50
 */
public interface RemoteService {
  String doSomethingWithArgs(String s);
  String doSomethingThrowException();
  String doSomethingSlowly();
  String doSomething();


  int process(int i);
}
