package com.example.notificationDemo.Repository;

import com.example.notificationDemo.model.TokenStorage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenStorageRepository extends CrudRepository<TokenStorage,Integer> {
//      TokenStorage findByUserIdAndPlatformIdAndSocialMediaId(String userId,String platformId,Integer socialMediaId);
    Optional<List<TokenStorage>> findByUserIdAndSocialMediaId(String userId,Integer socialMediaId);
    List<TokenStorage> findByUserId(String userId);
    Optional<TokenStorage> findByUserIdAndPlatformIdAndSocialMediaId(String userId,String platformId,Integer SocialMediaId);
}
