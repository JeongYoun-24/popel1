package com.example.gym.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name="pass")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pass {  // 출입장부

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pass_no")
    private Long passNo;                  // 출입부 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  // 폴딩키 (회원 아이디 )

    @Column(name="in_date")
    private LocalDateTime inDate;  // 출입날짜
    @Column(name="out_date")
    private LocalDateTime outDate;  // 퇴장 날짜




}
