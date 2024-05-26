package com.akshar_backend.repository;


import com.akshar_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByOtp(String otp);
    boolean existsByEmail(String email);
}
