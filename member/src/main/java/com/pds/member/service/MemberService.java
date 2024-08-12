package com.pds.member.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.pds.common.exception.BusinessException;
import com.pds.common.exception.BusinessExceptionEnum;
import com.pds.common.util.SnowUtil;
import com.pds.member.domain.Member;
import com.pds.member.domain.MemberExample;
import com.pds.member.mapper.MemberMapper;
import com.pds.member.req.MemberRegisterReq;
import com.pds.member.req.MemberSendCodeReq;
import jakarta.annotation.Resource;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger LOG= LoggerFactory.getLogger(MemberService.class);
    @Resource
    private MemberMapper memberMapper;


    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        //构造一个条件
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(list)) {
            //   return list.get(0).getId();
            //   避免抛出改成 RuntimeException
//            throw new RuntimeException("手机号已注册");
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIT);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }


    public void sendCode(MemberSendCodeReq req){
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        //构造一个条件
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        //如果手机号不存在 则插入记录
        if (CollUtil.isEmpty(list)) {
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }
        else{
            LOG.info("手机号码已存在！");
        }
        //生成验证码
        String code = RandomUtil.randomString(4);
        code="1234";

        LOG.info("------------>code:{}<------------------------------",code);
        //保存 短信记录表   手机号 短信吗 有效期 是否已使用  业务类型
        //                发送时间  使用时间

        //对接短信通道  发送短信 这里不深入了

    }
}
