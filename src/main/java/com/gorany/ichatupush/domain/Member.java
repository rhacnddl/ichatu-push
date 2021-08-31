package com.gorany.ichatupush.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString(exclude = {"joinList", "notificationList"})
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
    private String nickname;

    @Column
    private String password;

    private String email;

    private LocalDateTime loginDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "boolean default true")
    private Boolean available;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @Builder.Default
    private List<Join> joinList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver")
    @Builder.Default
    private List<Notification> notificationList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @Builder.Default
    private List<Profile> profileList = new ArrayList<>();

    /* Login시, 적용 */
    public void updateLoginDate(){
        this.loginDate = LocalDateTime.now();
    }

    public void deleteUser(){
        this.available = false;
        nickname = null;
    }


}
