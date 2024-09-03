package com.pds.business.domain;

import java.time.LocalDate;
import java.util.Date;

public class TrainTicket {
    private Long id;

    private Long trainId;

    private Integer ticketNum;

    private Integer saleFlag;

    private LocalDate saleDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainTicket{");
        sb.append("id=").append(id);
        sb.append(", trainId=").append(trainId);
        sb.append(", ticketNum=").append(ticketNum);
        sb.append(", saleFlag=").append(saleFlag);
        sb.append(", saleDate=").append(saleDate);
        sb.append('}');
        return sb.toString();
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Integer getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(Integer saleFlag) {
        this.saleFlag = saleFlag;
    }


}