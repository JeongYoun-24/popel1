package com.springboot.pople.dto;

import com.springboot.pople.entity.Movie;
import com.springboot.pople.entity.TimeTable;
import com.springboot.pople.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicktingDTO {

    private Long ticktingCode;  //영화 내역 번호
    private Long ticktingCount;  // 예약 인원수
    private String seatNumber; // 예약좌석 번호
    private LocalDateTime ticktingDate;
    private Long movieCode;  // 영화 번호
    private String userId; // 회원 아이디
    private Long timetableCode;  // 영화 상영 시간 번호


}







