package com.payday.payday.service;

import com.payday.payday.dto.RoomMetadataDto;
import com.payday.payday.entity.Member;
import com.payday.payday.entity.Room;

import java.util.HashMap;
import java.util.Optional;

public interface RoomService {


    Room createRoom(RoomMetadataDto dto);
    Room updateRoomMetadata(Long roomId, RoomMetadataDto dto);
    RoomMetadataDto getRoomMetadata(Long roomId);

    // all data
    Room getRoomById(Long roomId);

    // roomId로 receipt 접근, 등등 duplexRelation의 이유

    void deleteRoomById(Long roomId);





}
