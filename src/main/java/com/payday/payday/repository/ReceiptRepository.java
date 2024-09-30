package com.payday.payday.repository;

import com.payday.payday.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    //Use Mapping Only query_join, not call room + receipt + ...
    @Query("SELECT r FROM Receipt r WHERE r.room.id = :roomId AND r.receiptNumber = :receiptNumber")
    Optional<Receipt> findByRoomIdAndReceiptNumber(@Param("roomId") Long roomId, @Param("receiptNumber") int receiptNumber);


}
