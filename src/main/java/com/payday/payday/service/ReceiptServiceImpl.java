package com.payday.payday.service;

import com.payday.payday.entity.Receipt;
import com.payday.payday.entity.ReceiptContent;
import com.payday.payday.entity.Room;
import com.payday.payday.repository.ReceiptRepository;
import com.payday.payday.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final RoomRepository roomRepository;


    @Override
    public Receipt createReceipt(int receiptNumber, Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();

            Receipt receipt = new Receipt();
            receipt.setReceiptNumber(receiptNumber);
            receipt.setRoom(room);
            return receiptRepository.save(receipt);
        } else {
            throw new RuntimeException("Room이 유효하지 않습니다.");
        }
    }


    //skip
    @Override
    public Optional<Receipt> getReceipt(Long roomId, int receiptNumber) {
        return Optional.empty();
    }

    @Override
    public void deleteReceipt(Long roomId, int receiptNumber) {

    }
    // end - skip

    @Override
    public Receipt getReceiptById(Long receiptId) {
        Optional<Receipt> receiptOptional = receiptRepository.findById(receiptId);
        if (receiptOptional.isPresent()) {


            return receiptOptional.get();
        } else {
            throw new RuntimeException("Receipt가 존재하지 않습니다" + receiptId);
        }
    }


    @Override
    public void deleteReceiptById(Long receiptId) {
        Optional<Receipt> receiptOptional = receiptRepository.findById(receiptId);
        if (receiptOptional.isPresent()) {
            receiptRepository.delete(receiptOptional.get());

        } else {
            throw new RuntimeException("해당하는 영수증이 없습니다." + receiptId);
        }

    }


    // temp
    @Override
    public Receipt updateSettledMemberById(Long receiptId, String memberName) {
        Optional<Receipt> qs = receiptRepository.findById(receiptId);
        if (qs.isPresent()) {
            Receipt p = qs.get();
            p.getIsSettledMembers().add(memberName);
            return receiptRepository.save(p);

        } else {
            throw new RuntimeException("ㄴㄴ 오류임 ");
        }

    }

    @Transactional
    @Override
    public Receipt updateReceiptContentsById(Long receiptId, Receipt receipt) {
        Optional<Receipt> qs = receiptRepository.findById(receiptId);
        if (qs.isPresent()) {
            Receipt p = qs.get();

            //List Clear
            p.getReceiptContents().clear();
            //orphan DropTable : receiptContent
            Receipt flushed = receiptRepository.save(p);


            List<ReceiptContent> data = receipt.getReceiptContents();

            for (ReceiptContent res : data) {
                flushed.getReceiptContents().add(res);
                res.setReceipt(flushed);
            }
            return receiptRepository.save(flushed);


        } else {
            throw new RuntimeException("none");
        }

    }

}
