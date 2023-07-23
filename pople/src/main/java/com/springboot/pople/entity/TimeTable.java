package com.springboot.pople.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TimeTable")
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timetableno;
    private String timetableDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Time time;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

}
