package com.payday.payday.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Room {
    //generate ID -> indexing Table Creations , find by key(Linker) approximately O(1)
    @Id
    private Long roomId;

    private String generatedUrl;

    private String roomName;
    private String leader;
    private int memberCount;
    private int receiptCount;


    // additional Props? Metadatas
    private boolean isFinished;

    @OneToMany(mappedBy = "room",  cascade = CascadeType.ALL)
    @JsonIgnoreProperties("room")
    List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("room")
    List<Receipt> receipts = new ArrayList<>();



}
