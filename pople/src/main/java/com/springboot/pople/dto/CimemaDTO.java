package com.springboot.pople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CimemaDTO {

    private long cinemaCode;
    private String cinemaName;
    private String cinemaAddrss;
    private long cinemaSeatCount;


}
