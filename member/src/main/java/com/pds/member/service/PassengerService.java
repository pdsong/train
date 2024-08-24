package com.pds.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pds.common.context.LoginMemberContext;
import com.pds.common.util.SnowUtil;
import com.pds.member.domain.Passenger;
import com.pds.member.domain.PassengerExample;
import com.pds.member.mapper.PassengerMapper;
import com.pds.member.req.PassengerQueryReq;
import com.pds.member.req.PassengerSaveReq;
import com.pds.member.resp.PageResp;
import com.pds.member.resp.PassengerQueryResq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;


    public void save(PassengerSaveReq req){
        DateTime now=DateTime.now();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if(ObjectUtil.isNull(passenger.getId())){
            // SpringMvcConfig.java  MemberInterceptor.java LoginMemberContext
            // 就为了这一句  不需要传递当前会员id 使用拦截器取 使用现场本地变量存
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        }
        else{
            // 前端传什么更新什么 updateByPrimaryKey->空值更新为空   updateByPrimaryKeySelective->空值不更新
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }

    }


    public PageResp<PassengerQueryResq> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("id desc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }

        //mapper  对这句往下遇到的第一个SQL做拦截 增加分页limit
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Passenger> passengerList = passengerMapper.selectByExample(passengerExample);

        PageInfo<Passenger> pageInfo=new PageInfo<>(passengerList);
        List<PassengerQueryResq> list = BeanUtil.copyToList(passengerList, PassengerQueryResq.class);

        PageResp<PassengerQueryResq> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }


    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }
}
