package com.springboot.pople.controller;

import com.springboot.pople.dto.CinemaDTO;
import com.springboot.pople.dto.MovieDTO;
import com.springboot.pople.dto.SeatDTO;
import com.springboot.pople.dto.movie.MovieFormDTO;
import com.springboot.pople.dto.theater.TheaterFormDTO;
import com.springboot.pople.repository.movie.MovieRepository;
import com.springboot.pople.service.cinema.CinemaService;
import com.springboot.pople.service.movie.MovieService;
import com.springboot.pople.service.movie.MovieService2;
import com.springboot.pople.service.movieSchedule.MovieScheduleService;
import com.springboot.pople.service.seat.SeatService;
import com.springboot.pople.service.theater.TheaterFormService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final MovieScheduleService movieScheduleService;
    private final CinemaService cinemaService;
    private final MovieService movieService;
    private final MovieService2 movieService2;
    private final MovieRepository movieRepository;
    private final SeatService seatService;
    private final TheaterFormService theaterFormService;

    @GetMapping(value = "/form")
    public String getSchedule(Model model){

        return  "";
    }
    @PostMapping(value = "/form")
    public String postSchedule(Model model){

        return  "";
    }

    @GetMapping(value = "/find/{movieName}")
    public String getss(@PathVariable("movieName") String movieName, Model model){

        MovieDTO movieDTO = movieService.nameOne(movieName);
        log.info("fsdfsdfsdfsdfsd"+movieDTO);

        MovieFormDTO movieFormDTO =  movieService2.getMovieDtl(movieDTO.getMovieid());
        log.info("fsdfsdfsdfsdfsdss"+movieFormDTO);
        model.addAttribute("movie",movieFormDTO);
        log.info("예매 가즈아 ㄱㄱㄱㄱ");
        List<CinemaDTO> cinemaDTO =  cinemaService.AllList();
        log.info(cinemaDTO);
        model.addAttribute("cinema",cinemaDTO);
        List<SeatDTO> seatDTO = seatService.AllList();
        log.info(seatDTO);
        model.addAttribute("seat",seatDTO);

    List<TheaterFormDTO> formDTOList  =theaterFormService.AllList();
        log.info("1231231231231"+formDTOList);

        model.addAttribute("theater",formDTOList);


        return "views/schedule";
    }







}
