package com.pds.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class Test1Filter implements GlobalFilter, Ordered {

    private static final Logger LOG= LoggerFactory.getLogger(Test1Filter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOG.info("Test1Filter->");
        //过滤器链
        return chain.filter(exchange);
    }



    //过滤器顺序 多个过滤器  从小到大 先执行
    @Override
    public int getOrder() {
        return 1;
    }
}
