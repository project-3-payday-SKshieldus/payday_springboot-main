package com.payday.payday.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptContentId;

    // 영수증 관련 값들.. 검수 값만 들어옴. save case. 수정도 가능하긴 함
    private int price;
    private int quantity;

    // findBy?
    private String productName;

    // 굳이 없어도 ..
    private int sum;


    //need dto for response
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    @JsonIgnore
    //@JsonIgnoreProperties({"room", "receiptContents"})
    private Receipt receipt;

}
