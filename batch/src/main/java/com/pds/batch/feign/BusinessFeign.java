package com.pds.batch.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//通过这个类调用business模块
//business 对应到
// buiness的application.yml中的spring.application.name=business
//这种用法需要nacos
//@FeignClient("business")
@FeignClient(name = "business",url = "http://127.0.0.1:8002/business")
public interface BusinessFeign {

    @GetMapping("/station/ok")
     String helloOk();
}
