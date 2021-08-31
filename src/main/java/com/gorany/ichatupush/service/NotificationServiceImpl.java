package com.gorany.ichatupush.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.*;
import com.gorany.ichatupush.domain.Notification;
import com.gorany.ichatupush.domain.NotificationType;
import com.gorany.ichatupush.dto.NotificationDTO;
import com.gorany.ichatupush.exception.NoReceiverException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotificationServiceImpl implements NotificationService{

    private final static String CHAT_QUEUE_NAME = "chat-queue";
    private final static String COMMENT_QUEUE_NAME = "comment-queue";

    @Override
    @RabbitListener(queues = CHAT_QUEUE_NAME)
    public void sendChatNotification(final List<NotificationDTO> notificationDTOList) {
        System.out.println("notificationDTOList = " + notificationDTOList.size());
        if(notificationDTOList.isEmpty()) return;

        String senderNickname = notificationDTOList.get(0).getSenderNickname();
        /* Notification -> Tokens 추출 */
        List<String> tokenList = notificationDTOList.stream()
                .filter(nt -> (nt.getToken() != null && !nt.getToken().equals("")))
                .map(NotificationDTO::getToken)
                .collect(Collectors.toList());

        if(tokenList.size() < 1) return;
        tokenList.forEach(System.out::println);
        /* WebpushNotification */
        WebpushNotification webpushNotification = new WebpushNotification("채팅 알림", senderNickname + "님이 메세지를 보냈습니다.");

        /* create MulticastMessage : 메세지를 1개씩 각각 보내는 것보다 성능 Up */
        //https://firebase.google.com/docs/cloud-messaging/send-message?hl=ko
        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokenList)
                .putData("sender", senderNickname)
                .putData("type", "chat")
                .putData("senderProfileId", "")
                .putData("senderProfileName", "")
                .putData("senderProfilePath", "")
                .build();
//        MulticastMessage message = MulticastMessage.builder()
//                .addAllTokens(tokenList)
//                .setWebpushConfig(WebpushConfig.builder()
//                        .putHeader("TTL", "30")
//                        .setNotification(webpushNotification)
//                        .build())
//                .build();

        /* Send Message */
        BatchResponse response = null;

        try {
            response = FirebaseMessaging.getInstance().sendMulticastAsync(message).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        log.info("<- Received : " + response);
    }

    @Override
    @RabbitListener(queues = COMMENT_QUEUE_NAME)
    public void sendCommentNotification(final NotificationDTO notification) {

    }
}
