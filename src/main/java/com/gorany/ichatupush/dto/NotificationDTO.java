package com.gorany.ichatupush.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gorany.ichatupush.domain.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationDTO {

    //notification
    private Long id;
    private Long targetId;
    private NotificationType type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;
    private Character confirm;

    //sender
    private Long senderId;
    private String senderNickname;

    //profile
    private String senderProfileId;
    private String senderProfileName;
    private String senderProfilePath;

    //receiver
    private Long receiverId;
    private String receiverNickname;
    private String token;
}

