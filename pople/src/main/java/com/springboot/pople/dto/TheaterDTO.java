package com.springboot.pople.dto;

import com.springboot.pople.entity.Cinema;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheaterDTO {


    private Long id ;
    private Long cinemaid; // 영화관 번호



}
