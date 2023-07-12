package com.springboot.pople.repository;

import com.springboot.pople.dto.UsersDTO;
import com.springboot.pople.entity.Users;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository  extends JpaRepository<Users,String>, QuerydslPredicateExecutor<Users> {

//    List<Users> findByUser_email(String user_email);
//    @Query(value = "select i form Users i where i.user_email" )
//    List<Users> findByUser_email(@Param("user_email")String user_email);



}
