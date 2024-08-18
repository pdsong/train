package com.pds.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.pds.common.context.LoginMemberContext;
import com.pds.common.util.SnowUtil;
import com.pds.member.domain.Passenger;
import com.pds.member.mapper.PassengerMapper;
import com.pds.member.req.PassengerSaveReq;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;


    public void save(PassengerSaveReq req){
        DateTime now=DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        // SpringMvcConfig.java  MemberInterceptor.java LoginMemberContext
        // 就为了这一句  不需要传递当前会员id 使用拦截器取 使用现场本地变量存
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
