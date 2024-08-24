package com.pds.member.req;

import com.pds.request.PageRequest;

public class PassengerQueryReq extends PageRequest {

    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PassengerQueryReq{");
        sb.append("memberId=").append(memberId);
        sb.append('}');
        return sb.toString();
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
