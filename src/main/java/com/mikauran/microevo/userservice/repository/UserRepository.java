package com.mikauran.microevo.userservice.repository;

import com.mikauran.microevo.userservice.entity.UserMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<UserMicro,Long> {
    UserMicro findByUserId(Long userId);
}
