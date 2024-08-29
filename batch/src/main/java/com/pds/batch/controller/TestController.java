package com.pds.batch.controller;


import com.pds.batch.feign.BusinessFeign;
import com.pds.common.resp.CommonResp;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class TestController {

    @Resource
    BusinessFeign businessFeign;

    @GetMapping("/helloBusOK")
    public CommonResp<String>  hello(){
        String str = businessFeign.helloOk();
        System.out.println("Feign返回结果"+str);
        return new CommonResp<>(str);
    }


    @GetMapping("/ok")
    public CommonResp<String> ok() {
        return new CommonResp<>("OK");
    }

}
