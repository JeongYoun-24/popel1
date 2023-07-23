package com.springboot.pople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {

    private Long timeCode;  // 상영시간 코드
    private String satartTime;     // 상영 시간

}
