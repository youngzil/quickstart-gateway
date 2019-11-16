/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.quickstart.sentinel.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * @author jialiang.linjl
 */
public class FlowQpsDemo {

  private static final String KEY = "abc";

  private static AtomicInteger pass = new AtomicInteger();
  private static AtomicInteger block = new AtomicInteger();
  private static AtomicInteger total = new AtomicInteger();

  private static volatile boolean stop = false;

  private static final int threadCount = 32;

  private static int seconds = 60 + 40;

  public static void main(String[] args) throws Exception {
    initFlowQpsRule();

    while (!stop) {
      Entry entry = null;

      try {
        entry = SphU.entry(KEY);
        // token acquired, means pass
        pass.addAndGet(1);
      } catch (BlockException e1) {
        block.incrementAndGet();
      } catch (Exception e2) {
        // biz exception
      } finally {
        total.incrementAndGet();
        if (entry != null) {
          entry.exit();
        }
      }

      Random random2 = new Random();
      try {
        TimeUnit.MILLISECONDS.sleep(random2.nextInt(50));
      } catch (InterruptedException e) {
        // ignore
      }
    }

    System.out.println("===== begin to do flow control");
    System.out.println("only 20 requests per second can pass");

  }

  private static void initFlowQpsRule() {
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule1 = new FlowRule();
    rule1.setResource(KEY);
    // set limit qps to 20
    rule1.setCount(20);
    rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
    rule1.setLimitApp("default");
    rules.add(rule1);
    FlowRuleManager.loadRules(rules);
  }

}
