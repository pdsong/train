package com.pds.batch;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

//@EnableFeignClients("com.jiawa.train.business.feign")
@SpringBootApplication
@ComponentScan("com.pds")
@MapperScan("com.pds.*.mapper")
@EnableCaching
@EnableAsync
public class BatchApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));

        // // 限流规则
        // initFlowRules();
        // LOG.info("已定义限流规则");
    }

//    private static void initFlowRules(){
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule rule = new FlowRule();
//        rule.setResource("doConfirm");
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // Set limit QPS to 20.
//        rule.setCount(1);
//        rules.add(rule);
//        FlowRuleManager.loadRules(rules);
//    }
}