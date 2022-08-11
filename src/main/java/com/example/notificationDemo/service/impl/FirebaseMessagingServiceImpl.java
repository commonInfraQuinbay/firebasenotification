package com.example.notificationDemo.service.impl;

import com.example.notificationDemo.Repository.TokenStorageRepository;
import com.example.notificationDemo.model.Note;
import com.example.notificationDemo.model.TokenStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FirebaseMessagingServiceImpl implements com.example.notificationDemo.service.FirebaseMessagingService {

    @Autowired
    TokenStorageRepository tokenStorageRepository;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    ObjectMapper objectMapper=new ObjectMapper();

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public FirebaseMessagingServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    public String sendNotificationToMultipleDevice(Note note) throws FirebaseMessagingException,JsonProcessingException {
        if(note.getUserId()!=null) {
            List<String> tokens = new ArrayList<>();
            List<String> userId = note.getUserId();

                for(String id:userId){
                    Optional<List<TokenStorage>> tokenStorage = tokenStorageRepository.findByUserIdAndSocialMediaId(id, note.getSocialMediaId());
                   if(tokenStorage.isPresent()) {
                       for (TokenStorage tokenStorage1 : tokenStorage.get()) {
                           tokens.add(tokenStorage1.getToken());
                       }
                   }
                }

                if(tokens.size()!=0) {
                    Notification notification = Notification
                            .builder()
                            .setTitle(note.getTitle())
                            .setBody(note.getContent())
                            .build();

                    MulticastMessage message = MulticastMessage
                            .builder()
                            .addAllTokens(tokens)
                            .setNotification(notification)
                            .putAllData(note.getData())
                            .build();
                    BatchResponse response = firebaseMessaging.sendMulticast(message);


                    if (note.getTitle().equals("like") || note.getTitle().equals("comment")) {
                        kafkaTemplate.send("mail", objectMapper.writeValueAsString(note));
                    }

                    return response.toString();
                }
        }
        return "";
    }

    public String sendNotificationToTopic(Note note,String topic) throws FirebaseMessagingException {
        Notification notification = Notification
                .builder()
                .setTitle(note.getTitle())
                .setBody(note.getContent())
                .build();

        Message message =Message
                .builder()
                .setTopic(topic)
                .setNotification(notification)
                .putAllData(note.getData())
                .build();
        return firebaseMessaging.send(message);
    }

}