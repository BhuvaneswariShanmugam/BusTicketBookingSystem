package com.app.booking_system.repository;

import com.app.booking_system.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,String> {


     Users findByEmail(String email);


}
