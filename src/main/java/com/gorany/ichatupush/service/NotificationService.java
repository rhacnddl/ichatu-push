package com.gorany.ichatupush.service;

import com.gorany.ichatupush.dto.NotificationDTO;

import java.util.List;

public interface NotificationService{

    void sendChatNotification(final List<NotificationDTO> notificationDTOList);
    void sendCommentNotification(final NotificationDTO notification);
}
