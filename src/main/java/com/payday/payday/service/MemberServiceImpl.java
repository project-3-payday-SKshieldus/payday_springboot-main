package com.payday.payday.service;

import com.payday.payday.entity.*;
import com.payday.payday.repository.MemberRepository;
import com.payday.payday.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final RoomRepository roomRepository;
    private final MemberRepository memberRepository;

    @Override
    public Member createMember(String memberName, Long roomId) {
        Member member = new Member();
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            member.setMemberName(memberName);
            member.setRoom(roomOptional.get());
            return memberRepository.save(member);

        } else {
            throw new RuntimeException("유효하지 않은 room입니다 : " + roomId);
        }
    }


    @Override
    public Member getMemberByName(String memberName, Long roomId) {
        Optional<Member> qs = memberRepository.findByMemberName(memberName);
        if (qs.isPresent()) {
            return qs.get();
        } else {
            throw new RuntimeException("유효하지 않은 member이름 입니다 :s" + memberName);
        }
    }

    @Transactional
    @Override
    public Member updateReceiptContentsPerMemberByName(Long roomId, Member member) {
        Optional<Member> qs = memberRepository.findByRoomIdAndMemberName(roomId, member.getMemberName());
        if (qs.isPresent()) {
            Member p = qs.get();

            //List Clear
            p.getReceiptContentsPerMember().clear();
            //orphan DropTable : receiptContent
            Member flushed = memberRepository.save(p);


            List<ReceiptContentPerMember> data = member.getReceiptContentsPerMember();

            for (ReceiptContentPerMember res : data) {
                flushed.getReceiptContentsPerMember().add(res);
                res.setMember(flushed);
            }
            return memberRepository.save(flushed);


        } else {
            throw new RuntimeException("Member 개별 영수증 선택에 오류가 발생하였습니다." + roomId + "," + member.getMemberName());
        }
    }
}
