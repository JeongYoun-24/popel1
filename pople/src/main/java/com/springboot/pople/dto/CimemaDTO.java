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

    private long cinema_code;
    private String cinema_name;
    private String cinema_addrss;
    private long cinema_seat_count;


}
