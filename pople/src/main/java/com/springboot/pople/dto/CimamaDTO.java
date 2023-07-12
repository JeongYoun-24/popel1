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
public class CimamaDTO {

    private long cinama_code;
    private String cinama_name;
    private String cinama_addrss;
    private long cinama_seat_count;


}
