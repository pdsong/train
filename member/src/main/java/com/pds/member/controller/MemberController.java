package com.pds.member.controller;


import com.pds.common.resp.CommonResp;
import com.pds.member.mapper.MemberMapper;
import com.pds.member.req.MemberRegisterReq;
import com.pds.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
//        return memberService.count();
        int count = memberService.count();
        CommonResp<Integer> commonResp = new CommonResp<>();
        commonResp.setContent(count);
        return commonResp;
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid MemberRegisterReq req) {
        long registerID = memberService.register(req);
        return new CommonResp<>(registerID);  //CommResp(T content){ this.content=content }
    }
}
