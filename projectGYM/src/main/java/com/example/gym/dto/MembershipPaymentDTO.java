package com.example.gym.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MembershipPaymentDTO {

    private Long paymentNo;          /// 결제 아이디
    private String programId;         // 프로그램 아이디
    private String memberId;          // 회원 아이디
    private Long membershipNo;   // 회원권 번호

    private LocalDateTime PaymentDate;    // 결제 일자
    private int paymentPrice;             // 결제 금액
    private String paymentMethod;         // 결제 수단


}
