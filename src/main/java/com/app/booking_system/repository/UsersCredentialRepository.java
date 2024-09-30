package com.app.booking_system.repository;

import com.app.booking_system.entity.UsersCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersCredentialRepository extends JpaRepository<UsersCredential, String> {
     Optional<UsersCredential> findByEmail(String email);


}
