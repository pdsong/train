package com.pds.business.controller;


import com.pds.business.mapper.StationMapper;
import com.pds.business.resp.StationQueryResp;
import com.pds.business.service.StationService;
import com.pds.common.resp.CommonResp;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private StationService stationService;

    @GetMapping("/query-all")
    public CommonResp<List<StationQueryResp>> queryList() {
        List<StationQueryResp> list = stationService.queryAll();
        return new CommonResp<>(list);
    }
    @GetMapping("/ok")
    public CommonResp<String> ok() {
        List<StationQueryResp> list = stationService.queryAll();
        return new CommonResp<>("OK");
    }



}
