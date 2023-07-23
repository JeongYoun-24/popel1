package com.springboot.pople.dto;


import com.springboot.pople.dto.movie.MovieImgDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long movieCode;  // 영화 번호
    private String movieName;  //  양화 제목
    private String  moviePoster;  // 영화 이미지 (포스터)
    private String movieSummary; // 영화 줄거리
    private String movieTime; // 영화 러닝 타임
    private String moveiRating; // 영화 관람 등급
    private String movieDate;  //   영화 개봉일
    private Boolean movieStatus;  // 영화 게시 여부


    @Builder.Default
    private List<MovieImgDTO> imgDTOList = new ArrayList<>();
}
