package com.springboot.pople.repository;

import com.springboot.pople.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<Users,String> {



}
