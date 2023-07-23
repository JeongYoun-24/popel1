package com.springboot.pople.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@Table(name = "Time")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Time {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeno;  // 상영시간 코드
    @Column(nullable = false ,length = 50)
    private String startTime;     // 상영 시간


}



