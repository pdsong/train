package com.pds.news.service;
import com.pds.common.resp.PageResp;
import com.pds.news.domain.HackNew;
import com.pds.news.mapper.HackNewsMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HackNewService {

    @Autowired
    private HackNewsMapper hackNewsMapper;

    public PageResp<HackNew> queryList( ) {
        List<HackNew> newList = hackNewsMapper.queryAll();
        PageResp<HackNew> pageResp = new PageResp<>();
//        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(newList);
        return pageResp;
    }

}
