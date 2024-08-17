package com.pds.member.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.jwt.JWTUtil;
import com.pds.common.exception.BusinessException;
import com.pds.common.exception.BusinessExceptionEnum;
import com.pds.common.util.SnowUtil;
import com.pds.member.domain.Member;
import com.pds.member.domain.MemberExample;
import com.pds.member.mapper.MemberMapper;
import com.pds.member.req.MemberLoginReq;
import com.pds.member.req.MemberRegisterReq;
import com.pds.member.req.MemberSendCodeReq;
import com.pds.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        Member memberDB= selectByMobile(mobile);
        if (ObjectUtil.isNotEmpty(memberDB)) {
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


    public String sendCode(MemberSendCodeReq req){
        String mobile = req.getMobile();
        Member memberDB=   selectByMobile(mobile);
        //如果手机号不存在 则插入记录
        if (ObjectUtil.isEmpty(memberDB)) {
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
//        code="1234";

        LOG.info("------------>code:{}<------------------------------",code);
        //保存 短信记录表   手机号 短信吗 有效期 是否已使用  业务类型
        //                发送时间  使用时间

        //对接短信通道  发送短信 这里不深入了
        return code;
    }

    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);

        if (ObjectUtil.isEmpty(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MOBILE_NOT_EXIST);
        }

        //校验短信
//        if (!code.equals("1234")) {
//            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
//        }
        // hutool 升级 5.8.10支持列表copyProperties
        MemberLoginResp memberLoginResp =new MemberLoginResp();
        BeanUtil.copyProperties(memberDB, memberLoginResp);
        Map<String, Object> memberLoginMap = BeanUtil.beanToMap(memberLoginResp);
        String token = JWTUtil.createToken(memberLoginMap, "9999".getBytes());
        memberLoginResp.setToken(token);
        return memberLoginResp;

    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        //构造一个条件
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }else{
            return list.get(0);
        }
    }
}
