package com.springboot.pople.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tickting")
public class Tickting {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticktingno;  //영화 내역 번호
    @Column(nullable = false,length = 10)
    private Long ticktingCount;  // 예약 인원수
    @Column(nullable = false,length = 30)
    private String seatNumber; // 예약좌석 번호
    private LocalDateTime ticktingDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie; // 영화 번호
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users; // 회원 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    private TimeTable timeTable;  // 영화 상영 시간 번호

}
