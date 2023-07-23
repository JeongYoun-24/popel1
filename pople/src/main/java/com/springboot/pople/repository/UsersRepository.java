package com.springboot.pople.repository;

import com.springboot.pople.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UsersRepository  extends JpaRepository<Users,String>, QuerydslPredicateExecutor<Users> {


//    Users findByuser_email(String user_email);

//    Users findByEmail(String Email); // 이메일로 검색
    Users findByUserid(String userid);

//    @Query(value = "select i from Users i where i.user_id and i.user_email")
//    List<Users>findByUser_idOrUser_email(@Param("user_id") String user_id);


//    List<Users> findByUser_email(String user_email);
//    @Query(value = "select i form Users i where i.user_email" )
//    List<Users> findByUser_email(@Param("user_email")String user_email);



}
