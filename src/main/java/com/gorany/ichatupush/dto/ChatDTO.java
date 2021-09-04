package com.gorany.ichatupush.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
