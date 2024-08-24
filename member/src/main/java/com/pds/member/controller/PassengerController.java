package com.pds.member.controller;

import com.pds.common.context.LoginMemberContext;
import com.pds.common.resp.CommonResp;
import com.pds.member.req.PassengerQueryReq;
import com.pds.member.req.PassengerSaveReq;
import com.pds.member.resp.PageResp;
import com.pds.member.resp.PassengerQueryResq;
import com.pds.member.service.PassengerService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    // get 参数拼接到url 去掉 @RequestBody
    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResq>> queryList(@Valid PassengerQueryReq req){
        Long id = LoginMemberContext.getId();
        req.setMemberId(id);

        PageResp<PassengerQueryResq> pageResp = passengerService.queryList(req);
        return new CommonResp<>(pageResp);
    }
}
