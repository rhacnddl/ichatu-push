package com.gorany.ichatupush.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChatDTO {

    //chat
    private Long id;
    private String content;
    private LocalDateTime regDate;

    //member
    private Long memberId;
    private String nickname;

    //profile
    private Long profileId;
    private String name;
    private String path;

    //chatRoom
    private Long chatRoomId;

}
