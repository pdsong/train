package com.pds.member.controller;

import com.pds.common.resp.CommonResp;

import com.pds.member.req.PassengerSaveReq;
import com.pds.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<String> save(@Valid @RequestBody PassengerSaveReq req) {
       passengerService.save(req);
        return new CommonResp<>("nice");
    }
}
