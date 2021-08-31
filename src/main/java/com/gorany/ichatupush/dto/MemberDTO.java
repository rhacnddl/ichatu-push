package com.gorany.ichatupush.dto;

import com.gorany.ichatupush.domain.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberDTO {

    private Long id;

    private String password;
    private String nickname;
    private String email;

    private LocalDateTime loginDate;
    private LocalDateTime regDate;

    private Boolean available;

    private Role role;

    private List<ProfileDTO> profileDTOList = new ArrayList<>();
    private List<NotificationDTO> notificationDTOList = new ArrayList<>();

    private String token;
}
