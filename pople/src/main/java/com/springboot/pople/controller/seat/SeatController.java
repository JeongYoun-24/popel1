package com.springboot.pople.controller.seat;

import com.springboot.pople.service.movie.MovieService;
import com.springboot.pople.service.seat.SeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;


    @GetMapping("/form")
    public String getfind(){

        return "movie/movieFind";
    }


}
