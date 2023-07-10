package com.springboot.pople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UsersDTO {

    private String user_id;
    private String user_pwd;
    private String user_name;
    private String user_email;
    private String phone;
    private String birthdate;
    private LocalDateTime regdate;

}
