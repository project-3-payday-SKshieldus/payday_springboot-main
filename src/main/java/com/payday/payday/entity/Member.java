package com.payday.payday.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    @Column(name = "joined_at", columnDefinition = "TIMESTAMP", updatable = false)
    @CreationTimestamp
    private LocalDateTime joinedAt;


    private int totalPricePerMember;


    @OneToMany(mappedBy = "member",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("member")
    private List<ReceiptContentPerMember> receiptContentsPerMember = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    @JsonIgnore
    private Room room;

}
