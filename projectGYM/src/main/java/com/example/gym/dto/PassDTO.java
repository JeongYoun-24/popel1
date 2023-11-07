package com.example.gym.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PassDTO {


    private Long passNo;                  // 출입부 번호
    private String memberId;  // 폴딩키 (회원 아이디 )
    private LocalDateTime inDate;  // 출입날짜
    private LocalDateTime outDate;  // 퇴장 날짜

}
