package com.payday.payday.controller;

import com.payday.payday.entity.Member;
import com.payday.payday.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @PostMapping("/{roomId}")
    public ResponseEntity<Member> createMember(@PathVariable Long roomId, @RequestParam String memberName) {
        Member member = memberService.createMember(memberName, roomId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Member> getMemberByName(@PathVariable Long roomId,
                                                  @RequestParam String memberName) {
        Member member = memberService.getMemberByName(memberName, roomId);
        return ResponseEntity.ok(member);

    }
    @PutMapping("/{roomId}")
    public ResponseEntity<Member> updateReceiptContentsPerMember(@PathVariable Long roomId, @RequestBody Member memberReq) {
        Member member = memberService.updateReceiptContentsPerMemberByName(roomId, memberReq);
        return ResponseEntity.ok(member);
    }

}
