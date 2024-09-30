package com.payday.payday.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    //call receipt individually
    private int receiptNumber;

    // receipt data
    private String title;
    private String date;
    private String address;


    // 수정 필요 OR 내가 고른 정산 내역 코드도 작성필요
    @ElementCollection
    @CollectionTable(name = "is_settled_members", joinColumns = @JoinColumn(name="receipt_id"))
    @Column(name = "settled_member_name")
    private List<String> isSettledMembers = new ArrayList<>();

    // 필요 : ReceiptByMembers : localstorage -> String name -> QuerySet


    @OneToMany(mappedBy = "receipt",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("receipt")
    private List<ReceiptContent> receiptContents = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;


}
