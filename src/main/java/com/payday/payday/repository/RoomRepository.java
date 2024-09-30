package com.payday.payday.repository;


import com.payday.payday.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    // Metadata get
    //Optional<Room> findByRoomId(Long RoomId);


}
