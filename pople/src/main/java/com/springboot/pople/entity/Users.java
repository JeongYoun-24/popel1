package com.springboot.pople.entity;

import com.springboot.pople.constant.Role;
import com.springboot.pople.dto.UsersDTO;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
@ToString(exclude = "role" )
public class Users {

    @Id
    @Column(nullable = false)
    private String userid;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime regDate;

    // update(변경) aptjem 정의
    public void change(String user_pwd,String user_name,String user_email){
        this.password = password;
        this.name = name;
        this.email = email;

    }

    public static Users createMember(UsersDTO usersDTO, PasswordEncoder passwordEncoder){
        Users users = new Users();

        users.setName(usersDTO.getName());
        users.setEmail(usersDTO.getEmail());
        users.setUserid(usersDTO.getUserid());
        users.setPhone(usersDTO.getPhone());
        users.setBirthDate(usersDTO.getBirthDate());
        users.setRegDate(usersDTO.getRegDate());

        // 암호화
        String password = passwordEncoder.encode(usersDTO.getPassword());
        users.setPassword(password);
        users.setRole(Role.USER);
//        users.setRole(Role.ADMIN);
//        users.setRole(Role.MANAGER);

        return users;
    }




}
