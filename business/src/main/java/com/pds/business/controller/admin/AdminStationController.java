package com.pds.business.controller.admin;


import com.pds.business.req.StationQueryReq;
import com.pds.business.req.StationSaveReq;
import com.pds.business.resp.StationQueryResp;
import com.pds.business.service.StationService;
import com.pds.common.resp.CommonResp;
import com.pds.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 所有的管理控制台的请求带上admin 方便统一拦截或者放行
@RestController
@RequestMapping("/admin/station")
public class AdminStationController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody StationSaveReq req) {
        stationService.save(req);
        return new CommonResp<>();
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        stationService.delete(id);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> list = stationService.queryList(req);
        return new CommonResp<>(list);
    }

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
