package com.pds.gateway.config;

import com.pds.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoginMemberFilter implements GlobalFilter, Ordered {

    private static final Logger LOG= LoggerFactory.getLogger(LoginMemberFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path=exchange.getRequest().getURI().getPath();
//        System.out.println("path->"+path);
        if(path.contains("/admin")
                ||path.contains("/hello")
                ||path.contains("/member/member/login")
                ||path.contains("/member/member/sendCode")){
            LOG.info("不需要验证登录:{}",path);
            return chain.filter(exchange);
        }else {
            LOG.info("需要验证登录:{}",path);
        }
        //获取header的token参数
        String  token=exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("会员登录验证开始:token:{}",token);
        if(token==null||token.isEmpty()){
            LOG.info("token为空,请求被拦截!");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //校验token是否有效
        boolean validate= JwtUtil.validate(token);
        if(validate){
            LOG.info("token有效,放行!");
            return chain.filter(exchange);
        }
        else{
            LOG.info("token无效,拦截请求");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }



    //过滤器顺序 多个过滤器  从小到大 先执行
    @Override
    public int getOrder() {
        return 1;
    }
}
