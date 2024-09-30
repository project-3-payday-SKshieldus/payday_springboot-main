package com.payday.payday.controller;

import com.payday.payday.dto.RoomMetadataDto;
import com.payday.payday.entity.Member;
import com.payday.payday.entity.Receipt;
import com.payday.payday.entity.ReceiptContent;
import com.payday.payday.entity.Room;
import com.payday.payday.service.MemberService;
import com.payday.payday.service.ReceiptService;
import com.payday.payday.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;
    private ReceiptService receiptService;
    private MemberService memberService;


    // ************* room phase *************


    // initalizer
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody RoomMetadataDto roomMetadataDto) {
        Room room = roomService.createRoom(roomMetadataDto);
        return ResponseEntity.ok(room);
    }

    // ALL DATA
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        return ResponseEntity.ok(room);
    }

    // 그냥 getRoom 쓰는게 나을듯
    @GetMapping("/{roomId}/receipt")
    public ResponseEntity<List<Receipt>> getALLReceiptInRoom(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId);
        List<Receipt> tf = room.getReceipts();

        return ResponseEntity.ok(tf);
    }

    @GetMapping("/{roomId}/metadata")
    public ResponseEntity<RoomMetadataDto> getRoomMetadata(@PathVariable Long roomId) {
        RoomMetadataDto dto = roomService.getRoomMetadata(roomId);
        return ResponseEntity.ok(dto);

    }


    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoomMetadata(@PathVariable Long roomId,
                                                              @RequestBody RoomMetadataDto roomMetadataDto) {
        Room room = roomService.updateRoomMetadata(roomId, roomMetadataDto);
        return ResponseEntity.ok(room);
    }


    // orphanRemoval : ALL
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }

}
