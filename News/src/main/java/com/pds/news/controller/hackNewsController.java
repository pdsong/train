package com.pds.news.controller;



import com.pds.common.resp.CommonResp;
import com.pds.common.resp.PageResp;
import com.pds.news.domain.HackNew;
import com.pds.news.service.HackNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
public class hackNewsController {

    @Autowired
    private HackNewService hackNewService;

    @PostMapping("/hackNews")
    public CommonResp<Object> getHackNews() {
        PageResp<HackNew> hackNewPageResp = hackNewService.queryList();
        return new CommonResp<>(hackNewPageResp);
    }


}
