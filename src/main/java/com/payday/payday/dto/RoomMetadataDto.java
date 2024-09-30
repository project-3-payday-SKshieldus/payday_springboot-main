package com.payday.payday.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomMetadataDto {

    private Long roomId;
    private String generatedUrl;
    private String roomName;
    private String leader;
    private int memberCount;
    private int receiptCount;

}
