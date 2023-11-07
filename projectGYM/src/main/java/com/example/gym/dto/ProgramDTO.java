package com.example.gym.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDTO {


    private String programId;
    private String employeeId;
    private String memberId;
    private int inRegistrant;
    private int outRegistrant;
    private Date programDate;

}
