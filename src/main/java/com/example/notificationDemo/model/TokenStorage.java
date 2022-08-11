package com.example.notificationDemo.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
public class TokenStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String userId;
    String token;
    String platformId;
    Integer socialMediaId;
}
