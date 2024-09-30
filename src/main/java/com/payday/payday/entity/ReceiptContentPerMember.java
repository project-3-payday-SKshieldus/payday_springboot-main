package com.payday.payday.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptContentPerMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptContentPerMemberId;


    private String productName;
    private int price;
    private int quantity;

    private int orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;
}
