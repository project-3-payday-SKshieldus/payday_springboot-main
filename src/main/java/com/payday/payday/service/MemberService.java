package com.payday.payday.service;

import com.payday.payday.entity.Member;

import java.util.List;

public interface MemberService {
    public Member createMember(String memberName, Long roomId);
    //public Member addMember(Member member, Long roomId);

    public Member getMemberByName(String memberName, Long roomId);
    public Member updateReceiptContentsPerMemberByName(Long roomId,  Member member);

}
