package com.example.notificationDemo.controller;

import com.example.notificationDemo.model.Note;
import com.example.notificationDemo.service.impl.FirebaseMessagingServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {
    private final FirebaseMessagingServiceImpl firebaseService;

    public TestController(FirebaseMessagingServiceImpl firebaseService) {
        this.firebaseService = firebaseService;
    }


    @RequestMapping("/sendtodevices")
    @ResponseBody
    public String sendNotificationToMultipleDevice(@RequestBody Note note) throws FirebaseMessagingException,JsonProcessingException {
        return firebaseService.sendNotificationToMultipleDevice(note);
    }


    @RequestMapping("/sendtotopic")
    @ResponseBody
   public String sendNotificationToTopic(@RequestBody Note note,@RequestParam String topic) throws FirebaseMessagingException{
        return firebaseService.sendNotificationToTopic(note,topic);
    }

}
