package com.pds.member.service;


import com.pds.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Resource
    private MemberMapper memberMapper;



    public int count(){
        return memberMapper.count();
    }
}
