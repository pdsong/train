package com.pds.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pds.business.domain.*;
import com.pds.business.mapper.TrainMapper;
import com.pds.business.mapper.TrainTicketMapper;
import com.pds.business.req.TrainQueryReq;
import com.pds.business.req.TrainSaveReq;
import com.pds.business.req.TrainTicketQueryReq;
import com.pds.business.resp.TrainQueryResp;
import com.pds.business.resp.TrainStationQueryResp;
import com.pds.business.resp.TrainTicketQueryResp;
import com.pds.common.exception.BusinessException;
import com.pds.common.exception.BusinessExceptionEnum;
import com.pds.common.resp.PageResp;
import com.pds.common.util.SnowUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainTicketService {
    private static final Logger LOG = LoggerFactory.getLogger(TrainTicketService.class);

    @Resource
    private TrainTicketMapper trainTicketMapper;


    @Resource
    private TrainService trainService;

    @Resource
    private TrainMapper trainMapper;

    public void insert1000TrainTicket(Long trainId, LocalDate queryDate) throws ParseException {
        TrainTicket trainTicket = new TrainTicket();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= 100; i++) {
            trainTicket.setId(SnowUtil.getSnowflakeNextId());
            trainTicket.setTicketNum(i);
            trainTicket.setSaleDate(queryDate);
            trainTicket.setTrainId(trainId);
            trainTicket.setSaleFlag(0);
            trainTicketMapper.insert(trainTicket);
        }

    }

    List<TrainTicketQueryResp> getEachTrainRestTicket(Date queryDate) {
        TrainTicketExample trainTicketExample = new TrainTicketExample();
//        trainStationExample.setOrderByClause("train_code asc, `index` asc");
        TrainTicketExample.Criteria criteria = trainTicketExample.createCriteria();
        if (ObjectUtil.isNotEmpty(queryDate)) {
            criteria.andSaleDateEqualTo(queryDate);
        }
//        criteria.and
        long total = trainTicketMapper.countByExample(trainTicketExample);
        return null;
    }

    public static Date convertToDateAtEndOfDay(LocalDate localDate) {
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    private static Date convertToDate(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    public PageResp<TrainTicketQueryResp> queryPageByParam(TrainTicketQueryReq req) throws ParseException {
        //查询时间 没有则取当天时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate queryDate = req.getDate();
        if (ObjectUtil.isNull(req.getDate())) {
            queryDate = LocalDate.now();
        }
        // 查询时间的所有车站车票的记录数 等于0则说明没生成 需要生成  有就跳过
        TrainTicketExample trainTicketExample = new TrainTicketExample();
//        trainStationExample.setOrderByClause("train_code asc, `index` asc");
        TrainTicketExample.Criteria criteria = trainTicketExample.createCriteria();

        // 获取一天的开始时刻
        LocalDateTime startOfDay = queryDate.atStartOfDay();
        Date startDate = convertToDate(startOfDay);

        // 获取一天的结束时刻
        LocalDateTime endOfDay = queryDate.atTime(LocalTime.MAX);
        Date endDate = convertToDate(endOfDay);

        criteria.andSaleDateBetween(startDate, endDate);

        long total = trainTicketMapper.countByExample(trainTicketExample);
        List<TrainQueryResp> trainList = trainService.queryAll();
        List<Long> trainIdList=trainList.stream().map(TrainQueryResp::getId).collect(Collectors.toList());
        if (total == 0) {
            for (TrainQueryResp trainResp : trainList) {
                insert1000TrainTicket(trainResp.getId(), queryDate);
            }
        }

        List<TrainTicketQueryResp> respList=trainTicketMapper.queryPageByDate(trainIdList,startDate,endDate);

        PageResp<TrainTicketQueryResp> page=new PageResp<>();
        page.setList(respList);
        page.setTotal(10L);
        return page;
    }

    public void orderTic(TrainTicketQueryReq req) {
        String trainId = req.getTrainId();
        LocalDate date = req.getDate();
        TrainTicket trainTicket=getOneByParam(trainId,date);
        if(ObjectUtil.isNotNull(trainTicket)){
            trainTicket.setSaleFlag(1);
            trainTicketMapper.updateByPrimaryKey(trainTicket);
        }else {
            throw new RuntimeException("票已经售罄");
        }
    }

    private TrainTicket getOneByParam(String trainId, LocalDate date) {
        if(ObjectUtil.isNull(date)){
            date=  LocalDate.now();
        }
        // 获取一天的开始时刻
        LocalDateTime startOfDay = date.atStartOfDay();
        Date startDate = convertToDate(startOfDay);

        // 获取一天的结束时刻
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
        Date endDate = convertToDate(endOfDay);
        TrainTicketExample trainTicketExample = new TrainTicketExample();
        TrainTicketExample.Criteria criteria = trainTicketExample.createCriteria();
        criteria.andSaleDateBetween(startDate, endDate);
        criteria.andTrainIdEqualTo(Long.valueOf(trainId));
        criteria.andSaleFlagEqualTo(0);
        List<TrainTicket> trainTickets = trainTicketMapper.selectByExample(trainTicketExample);
        if(trainTickets.size()>0){
         return trainTickets.get(0);
        }
        return new TrainTicket();
    }


//    public void save(TrainSaveReq req) {
//        DateTime now = DateTime.now();
//        Train train = BeanUtil.copyProperties(req, Train.class);
//        if (ObjectUtil.isNull(train.getId())) {
//
//            // 保存之前，先校验唯一键是否存在
//            Train trainDB = selectByUnique(req.getCode());
//            if (ObjectUtil.isNotEmpty(trainDB)) {
//                throw new BusinessException(BusinessExceptionEnum.BUSINESS_TRAIN_CODE_UNIQUE_ERROR);
//            }
//
//            train.setId(SnowUtil.getSnowflakeNextId());
//            train.setCreateTime(now);
//            train.setUpdateTime(now);
//            trainMapper.insert(train);
//        } else {
//            train.setUpdateTime(now);
//            trainMapper.updateByPrimaryKey(train);
//        }
//    }
//
//    private Train selectByUnique(String code) {
//        TrainExample trainExample = new TrainExample();
//        trainExample.createCriteria().andCodeEqualTo(code);
//        List<Train> list = trainMapper.selectByExample(trainExample);
//        if (CollUtil.isNotEmpty(list)) {
//            return list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//
//
//    public PageResp<TrainQueryResp> queryList(TrainQueryReq req) {
//        TrainExample trainExample = new TrainExample();
//        trainExample.setOrderByClause("code desc");
//        TrainExample.Criteria criteria = trainExample.createCriteria();
//
//        LOG.info("查询页码：{}", req.getPage());
//        LOG.info("每页条数：{}", req.getSize());
//        PageHelper.startPage(req.getPage(), req.getSize());
//        List<Train> trainList = trainMapper.selectByExample(trainExample);
//
//        PageInfo<Train> pageInfo = new PageInfo<>(trainList);
//        LOG.info("总行数：{}", pageInfo.getTotal());
//        LOG.info("总页数：{}", pageInfo.getPages());
//
//        List<TrainQueryResp> list = BeanUtil.copyToList(trainList, TrainQueryResp.class);
//
//        PageResp<TrainQueryResp> pageResp = new PageResp<>();
//        pageResp.setTotal(pageInfo.getTotal());
//        pageResp.setList(list);
//        return pageResp;
//    }
//
//    public void delete(Long id) {
//        trainMapper.deleteByPrimaryKey(id);
//    }
//
//
//    @Transactional
//    public List<TrainQueryResp> queryAll() {
//        TrainExample trainExample = new TrainExample();
//        trainExample.setOrderByClause("code asc");
//        List<Train> trainList = trainMapper.selectByExample(trainExample);
//        return BeanUtil.copyToList(trainList, TrainQueryResp.class);
//    }

}
