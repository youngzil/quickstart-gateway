package org.quickstart.snowjena;

import cn.yueshutong.commoon.enums.LimiterModel;
import cn.yueshutong.core.config.RateLimiterConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yueshutong.commoon.entity.RateLimiterRule;
import cn.yueshutong.commoon.entity.RateLimiterRuleBuilder;
import cn.yueshutong.commoon.enums.RuleAuthority;
import cn.yueshutong.core.limiter.RateLimiter;
import cn.yueshutong.core.limiter.RateLimiterFactory;

/**
 * Unit test for simple App.
 */
public class RateLimiterTest {

  Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * 本地限流,本项目提供了简单易用的API，对于本地限流，只需要通过工厂模式生产限流器，在需要限流的代码运行之前调用tryAcquire()方法即可。
   */
  @Test
  public void test1() {
    // 1.配置规则
    RateLimiterRule rateLimiterRule = new RateLimiterRuleBuilder()//
        .setLimit(1)//
        .setPeriod(1)//
        .setUnit(TimeUnit.SECONDS) // 每秒令牌数为1
        .build();
    // 2.工厂模式生产限流器
    RateLimiter limiter = RateLimiterFactory.of(rateLimiterRule);
    // 3.使用
    while (true) {
      if (limiter.tryAcquire()) {
        logger.info("ok");
      }
    }
  }

  /**
   * 黑名单,黑白名单功能为：对黑名单不允许通过，或只对白名单允许通过（二选一）。
   */
  @Test
  public void test2() {
    // 1.配置规则
    RateLimiterRule rateLimiterRule = new RateLimiterRuleBuilder().setLimit(1).setRuleAuthority(RuleAuthority.AUTHORITY_BLACK) // 黑名单
        .setLimitUser(new String[] {"user1", "user2"}).build();
    // 2.工厂模式生产限流器
    RateLimiter limiter = RateLimiterFactory.of(rateLimiterRule);
    // 3.使用
    while (true) {
      if (limiter.tryAcquire("user1")) {
        logger.info("user1");
      }
      if (limiter.tryAcquire("user2")) {
        logger.info("user2");
      }
      if (limiter.tryAcquire("user3")) {
        logger.info("user3");
      }
    }

  }


  /**
   * 分布式限流
   */
  @Test
  public void test4() throws InterruptedException {
    // 1.限流配置
    RateLimiterRule rateLimiterRule = new RateLimiterRuleBuilder()
        .setApp("Application")
        .setId("myId")
        .setLimit(1) //每秒1个令牌
        .setBatch(1) //每批次取1个令牌
        .setLimiterModel(LimiterModel.CLOUD) //分布式限流,需启动TicketServer控制台
        .build();
    // 2.配置TicketServer地址（支持集群、加权重）
    Map<String, Integer> map = new HashMap<>();
    map.put("127.0.0.1:8521", 1);
    // 3.全局配置
    RateLimiterConfig config = RateLimiterConfig.getInstance();
    config.setTicketServer(map);
    // 4.工厂模式生产限流器
    RateLimiter limiter = RateLimiterFactory.of(rateLimiterRule, config);
    // 5.使用
    while (true) {
      if (limiter.tryAcquire()) {
        logger.info("ok");
      }
      Thread.sleep(10);
    }
  }

}
