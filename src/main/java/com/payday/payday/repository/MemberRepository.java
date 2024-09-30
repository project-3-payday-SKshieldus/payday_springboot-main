package com.payday.payday.repository;

import com.payday.payday.entity.Member;
import com.payday.payday.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE m.room.id = :roomId AND m.memberName = :memberName")
    Optional<Member> findByRoomIdAndMemberName(@Param("roomId") Long roomId, @Param("memberName") String memberName);

    Optional<Member> findByMemberName(String memberName);
}
