package com.springboot.pople.entity;

import lombok.*;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cinamano;
    @Column(nullable = false,length = 50)
    private String cinamaName;
    @Column(nullable = false,length = 500)
    private String cinamaAddrss;
    @Column(nullable = false)
    private long cinamaSeatCount;

    public void change(String cinama_name,String cinama_addrss,long cinama_seat_count){
        this.cinamaName = cinamaName;
        this.cinamaAddrss = cinamaAddrss;
        this.cinamaSeatCount = cinamaSeatCount;

    }



}




