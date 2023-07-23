package com.springboot.pople.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatno;
    private String seatNumber;
    private boolean seatStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private TimeTable timeTable;

}
