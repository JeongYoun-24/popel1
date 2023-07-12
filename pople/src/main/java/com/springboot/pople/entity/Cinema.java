package com.springboot.pople.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CINEMA")
public class Cinema {

    @Id
    @Column(nullable = false)
    private long cinama_code;
    @Column(nullable = false,length = 50)
    private String cinama_name;
    @Column(nullable = false,length = 500)
    private String cinama_addrss;
    @Column(nullable = false)
    private long cinama_seat_count;

}
