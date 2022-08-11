package com.example.notificationDemo.service;

import com.example.notificationDemo.model.Note;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface FirebaseMessagingService {
    public String sendNotificationToMultipleDevice(Note note) throws FirebaseMessagingException,JsonProcessingException;
    public String sendNotificationToTopic(Note note,String topic) throws FirebaseMessagingException;
}
