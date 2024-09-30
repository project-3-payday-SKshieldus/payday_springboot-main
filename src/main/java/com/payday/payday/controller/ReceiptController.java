package com.payday.payday.controller;


import com.payday.payday.entity.Receipt;
import com.payday.payday.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt")
@AllArgsConstructor
public class ReceiptController {

    private ReceiptService receiptService;

    @PostMapping("/{roomId}")
    public ResponseEntity<Receipt> createReceipt(@RequestParam int receiptNumber,
                                                 @RequestParam Long roomId) {

        Receipt receipt = receiptService.createReceipt(receiptNumber, roomId);
        return ResponseEntity.ok(receipt);
    }


    // 개별 Get
    @GetMapping("/{roomId}/{receiptNumber}")
    public void getReceipt() {}


    // Room Link 생성 후(데이터 정제 완료), Member들이 Room data를 로딩할 경우
    @GetMapping("/{receiptId}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Long receiptId) {
        Receipt receipt = receiptService.getReceiptById(receiptId);
        return ResponseEntity.ok(receipt);
    }


    @PutMapping("/{receiptId}")
    public ResponseEntity<Receipt> updateReceipt(@PathVariable Long receiptId,
                                                 @RequestBody Receipt receipt) {
        Receipt res = receiptService.updateReceiptContentsById(receiptId, receipt);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/{receiptId}/{memberName}")
    public ResponseEntity<Receipt> updateSettledMember(@PathVariable Long receiptId,
                                                       @PathVariable String memberName) {
        Receipt res = receiptService.updateSettledMemberById(receiptId, memberName);
        return ResponseEntity.ok(res);
    }
}
