package com.springboot.theater;

import com.springboot.pople.PopleApplication;
import com.springboot.pople.dto.TheaterDTO;
import com.springboot.pople.entity.Theater;
import com.springboot.pople.repository.TheaterRepository;
import com.springboot.pople.service.theater.TheaterService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Log4j2
@SpringBootTest(classes = PopleApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class theaterTest {

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private TheaterService theaterService;


    @Test
    @DisplayName(value = "상영관 등록 테스트 ")
    public void theaterTest(){
        Long cinemaid = 2L;

        TheaterDTO theaterDTO = TheaterDTO.builder()
                .cinemaid(cinemaid)
                .build();

        log.info(theaterDTO);
        theaterService.register(theaterDTO);

    }



}
