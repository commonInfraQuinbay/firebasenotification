package com.example.notificationDemo.service.impl;

import com.example.notificationDemo.Repository.TokenStorageRepository;
import com.example.notificationDemo.model.TokenStorage;
import com.example.notificationDemo.service.TokenStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.tools.jstat.Token;

import java.util.List;
import java.util.Optional;

@Service
public class TokenStorageServiceImpl implements TokenStorageService {
    @Autowired
    TokenStorageRepository tokenStorageRepository;

    @Override
    public TokenStorage saveToken(TokenStorage tokenStorage) {
        Optional<TokenStorage> optionalTokenStorage=tokenStorageRepository.findByUserIdAndPlatformIdAndSocialMediaId(tokenStorage.getUserId(),tokenStorage.getPlatformId(),tokenStorage.getSocialMediaId());
        if(optionalTokenStorage.isPresent()){
                    TokenStorage tokenStorage1=optionalTokenStorage.get();
                    tokenStorage1.setToken(tokenStorage.getToken());
                    return tokenStorageRepository.save(tokenStorage1);
        }
        return tokenStorageRepository.save(tokenStorage);
    }
}
