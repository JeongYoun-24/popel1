package com.project.bank.dto;

import com.project.bank.entity.Member;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AccoutDTO {   // 계좌 번호 생성 DTO


    private String accountNumber;       // 계좌번호
    private LocalDateTime createDate;   // 생성 일자
    private int balance;                // 잔액

    private Member member;              // 회원 아이디



}
