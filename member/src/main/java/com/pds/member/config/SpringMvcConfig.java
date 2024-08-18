package com.pds.member.config;


import com.pds.common.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    MemberInterceptor memberInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //加入拦截器 这样才能生效
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns("/**") //针对所有的请求接口生效 除了下面两个接口
                .excludePathPatterns(
                        "/member/member/sendCode",
                        "/member/member/login"
                );
    }
}
