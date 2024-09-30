package com.payday.payday.service;

import com.payday.payday.dto.RoomMetadataDto;

import com.payday.payday.entity.Room;
import com.payday.payday.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room createRoom(RoomMetadataDto dto) {
        Room room = Room.builder()
                .roomId(dto.getRoomId())
                .roomName(dto.getRoomName())
                .memberCount(dto.getMemberCount())
                .leader(dto.getLeader())
                .generatedUrl(dto.getGeneratedUrl())
                .build();
        return roomRepository.save(room);
    }


    // 추후 기능 보완 필요, base 코드
    @Override
    public Room updateRoomMetadata(Long roomId, RoomMetadataDto dto) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setRoomName(dto.getRoomName());
            room.setLeader(dto.getLeader());
            return roomRepository.save(room);
        } else {
            throw new RuntimeException("해당하는 Room이 없습니다: " + roomId);
        }
    }

    // 추후 dtoProjection으로 최적화?
    @Override
    public RoomMetadataDto getRoomMetadata(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            return RoomMetadataDto.builder()
                    .roomName(room.getRoomName())
                    .generatedUrl(room.getGeneratedUrl())
                    .build();
        } else {
            throw new RuntimeException("해당하는 Room이 없습니다: " + roomId);
        }


    }

    @Override
    public Room getRoomById(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            return roomOptional.get();
        } else {
            throw new RuntimeException("해당하는 Room이 없습니다: " + roomId);
        }
    }

    @Override
    public void deleteRoomById(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (roomOptional.isPresent()) {
            roomRepository.delete(roomOptional.get());
        } else {
            throw new RuntimeException("해당하는 Room이 없습니다: " + roomId);
        }
    }



}
