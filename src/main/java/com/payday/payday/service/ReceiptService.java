package com.payday.payday.service;

import com.payday.payday.entity.Receipt;
import com.payday.payday.entity.ReceiptContent;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    // initializer
    public Receipt createReceipt(int receiptNumber, Long roomId);

    // 어떤 상황에서든 조회가능한 jpql Query, 비상 시 코드
    public Optional<Receipt> getReceipt(Long roomId, int receiptNumber);
    public void deleteReceipt(Long roomId, int receiptNumber);

    // by id
    public Receipt getReceiptById(Long receiptId);
    public void deleteReceiptById(Long receiptId);


    // 현재 정산한 인원 체크, 사용자 간 지속적 반영이 필요하기 때문
    public Receipt updateSettledMemberById(Long receiptId, String memberName);


    //현재 자동 key receiptContent는 모든 블럭을 추적하는 것 보다, 새로 만드는게 나음
    /*
       만약 여건이 된다면, 항상 순서를 유지하는 key를 통해,
       dirty Check를 통한부분 업데이트 가능
    */
    public Receipt updateReceiptContentsById(Long receiptId, Receipt receipt);



}
