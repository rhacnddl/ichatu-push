package com.gorany.ichatupush.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomDTO {

    //chatRoom
    private Long id;
    private String name;
    private LocalDateTime regDate;

    //member
    private Long memberId;
    private String nickname;

    //region
    private Long regionId;
    //city
}
