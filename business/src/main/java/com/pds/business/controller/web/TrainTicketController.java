package com.pds.business.controller.web;


import com.pds.business.mapper.TrainTicketMapper;
import com.pds.business.req.TrainTicketQueryReq;
import com.pds.business.resp.TrainQueryResp;

import com.pds.business.resp.TrainTicketQueryResp;
import com.pds.business.service.TrainTicketService;
import com.pds.common.resp.CommonResp;
import com.pds.common.resp.PageResp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/web/trainTicket")
public class TrainTicketController {


    @Autowired
    private TrainTicketService trainTicketService;

    /**
     * 查询接受日期参数
     * 所有的train信息+余票
     * 没有则为每个train新建1000张票
     * @param req
     * @return
     */
    @GetMapping("/queryByDate")
    public CommonResp<Object> save(@Valid  TrainTicketQueryReq req) throws ParseException {
        PageResp<TrainTicketQueryResp> page = trainTicketService.queryPageByParam(req);
        return new CommonResp<>(page);
    }

    /**
     * 购票
     * 用户id  trainId 车次 日期
     * trainId  ticketNum()
     * @param req
     * @return
     */
    @PostMapping("/orderTic")
    public CommonResp<Object> orderTicket( @RequestBody TrainTicketQueryReq req) {
        trainTicketService.orderTic(req);
        return new CommonResp<>("购票成功");
    }
//
//    /**
//     * 退票
//     *  用户id  trainId 车次 日期
//     *  trainId  ticketNum()
//     * @param req
//     * @return
//     */
//    @PostMapping("/save")
//    public CommonResp<Object> save(@Valid @RequestBody ConfirmOrderDoReq req) {
//        confirmOrderService.save(req);
//        return new CommonResp<>();
//    }
//
//    /**
//     * 用户购票信息查询
//     * 用户id 车票num  车次信息
//     */
//    @PostMapping("/save")
//    public CommonResp<Object> save(@Valid @RequestBody ConfirmOrderDoReq req) {
//        confirmOrderService.save(req);
//        return new CommonResp<>();
//    }
}
