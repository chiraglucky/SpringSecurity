package com.multipleauthentication.Repository;

import com.multipleauthentication.Model.UserSecretKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSecretKeyRepository extends JpaRepository<UserSecretKey,Integer> {
    Optional<UserSecretKey> findByUsername(String username);
}
