package com.example.notificationDemo.controller;

import com.example.notificationDemo.model.TokenStorage;
import com.example.notificationDemo.service.TokenStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/token")
public class TokenStorageController {

   @Autowired
   TokenStorageService tokenStorageService;


    @PostMapping(value = "/save")
    public TokenStorage saveToken(@RequestBody TokenStorage tokenStorage){
        return tokenStorageService.saveToken(tokenStorage);
    }


}
