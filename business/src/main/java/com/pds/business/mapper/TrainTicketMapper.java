package com.pds.business.mapper;

import com.pds.business.domain.TrainTicket;
import com.pds.business.domain.TrainTicketExample;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.pds.business.resp.TrainTicketQueryResp;
import org.apache.ibatis.annotations.Param;

public interface TrainTicketMapper {
    long countByExample(TrainTicketExample example);

    int deleteByExample(TrainTicketExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TrainTicket record);

    int insertSelective(TrainTicket record);

    List<TrainTicket> selectByExample(TrainTicketExample example);

    TrainTicket selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TrainTicket record, @Param("example") TrainTicketExample example);

    int updateByExample(@Param("record") TrainTicket record, @Param("example") TrainTicketExample example);

    int updateByPrimaryKeySelective(TrainTicket record);

    int updateByPrimaryKey(TrainTicket record);

    List<TrainTicketQueryResp> queryPageByDate(@Param("idList")List<Long> idList, Date start, Date end );


}