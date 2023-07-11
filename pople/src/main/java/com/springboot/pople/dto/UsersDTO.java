package com.springboot.pople.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UsersDTO {
    @NotEmpty
    private String user_id;
    @NotEmpty
    private String user_pwd;
    @NotEmpty
    private String user_name;
    @NotEmpty
    private String user_email;
    private String phone;
    private String birthdate;
    private LocalDateTime regdate;

}
