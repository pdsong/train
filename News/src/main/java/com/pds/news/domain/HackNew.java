package com.pds.news.domain;

import java.util.Date;

public class HackNew {
    private String cnTitle;
    private String enTitle;
    private String link;

    public String getCnTitle() {
        return cnTitle;
    }

    public void setCnTitle(String cnTitle) {
        this.cnTitle = cnTitle;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    private Date inTime;
}
