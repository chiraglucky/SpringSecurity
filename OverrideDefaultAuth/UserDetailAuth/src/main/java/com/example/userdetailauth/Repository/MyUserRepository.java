package com.example.userdetailauth.Repository;

import com.example.userdetailauth.Entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    Optional<MyUser> findByUsername(String username);
}
