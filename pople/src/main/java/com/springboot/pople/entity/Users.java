package com.springboot.pople.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @Column(nullable = false,length = 50)
    private String user_id;
    @Column(nullable = false,length = 50)
    private String user_pwd;
    @Column(nullable = false,length = 50)
    private String user_name;
    @Column(nullable = false,length = 50)
    private String user_email;
    @Column(nullable = false,length = 20)
    private String phone;
    @Column(nullable = false,length = 20)
    private String birthdate;
    private LocalDateTime regdate;

    // update(변경) aptjem 정의
    public void change(String user_pwd,String user_name,String user_email){
        this.user_pwd = user_pwd;
        this.user_name = user_name;
        this.user_email = user_email;

    }


}
