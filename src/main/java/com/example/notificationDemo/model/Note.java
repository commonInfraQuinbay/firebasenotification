package com.example.notificationDemo.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Note {

    private List<String> userId;
    private String title;
    private String content;
    private Map<String,String> data;
    private List<String> emails;//
    Integer socialMediaId;//
}
