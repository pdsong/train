package com.pds.business.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pds.common.req.PageReq;

import java.time.LocalDate;
import java.util.Date;

public class TrainTicketQueryReq extends PageReq {


    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate date;

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    private String trainId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainTicketQueryReq{");
        sb.append("date='").append(date).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
