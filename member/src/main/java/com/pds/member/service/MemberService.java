package com.pds.member.service;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.pds.common.exception.BusinessException;
import com.pds.common.exception.BusinessExceptionEnum;
import com.pds.common.util.SnowUtil;
import com.pds.member.domain.Member;
import com.pds.member.domain.MemberExample;
import com.pds.member.mapper.MemberMapper;
import com.pds.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
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
}
