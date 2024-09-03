package com.pds.business.resp;

import java.time.LocalDate;

public class TrainTicketQueryResp {
    private String id;

    public String getTrainId() {
        return trainId;
    }

    private String trainId;

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    private Integer saleFlag;

    private LocalDate saleDate;

    private Integer ticketRestNum;

    private String code;
    private String type;
    private String start;
    private String end;

    private String startTime;
    private String endTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Integer saleFlag) {
        this.saleFlag = saleFlag;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getTicketRestNum() {
        return ticketRestNum;
    }

    public void setTicketRestNum(Integer ticketRestNum) {
        this.ticketRestNum = ticketRestNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
