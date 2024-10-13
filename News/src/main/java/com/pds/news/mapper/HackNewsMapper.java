package com.pds.news.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.pds.news.domain.HackNew;

import java.util.List;

@Mapper
public interface HackNewsMapper {

    @Select("SELECT cn_title AS cnTitle, en_title AS enTitle, link FROM hacknew")
    List<HackNew> queryAll();

}
