package com.pds.batch.controller;


import com.pds.common.resp.CommonResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class TestController {


    @GetMapping("/ok")
    public CommonResp<String> ok() {
        return new CommonResp<>("OK");
    }

}
