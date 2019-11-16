package org.quickstart.sentinel.basic;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/7 16:22
 */
public class BasicTest {

  public static void main(String[] args) {
    initFlowRules();
    while (true) {
      Entry entry = null;
      try {
        entry = SphU.entry("HelloWorld");
        /*您的业务逻辑 - 开始*/
        System.out.println("hello world");
        /*您的业务逻辑 - 结束*/
      } catch (BlockException e1) {
        /*流控逻辑处理 - 开始*/
        System.out.println("block!");
        /*流控逻辑处理 - 结束*/
      } finally {
        if (entry != null) {
          entry.exit();
        }
      }
    }
  }

  private static void initFlowRules(){
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule = new FlowRule();
    rule.setResource("HelloWorld");
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    // Set limit QPS to 20.
    rule.setCount(20);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
  }

}
