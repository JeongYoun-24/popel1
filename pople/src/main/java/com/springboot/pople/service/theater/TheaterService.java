package com.springboot.pople.service.theater;

import com.springboot.pople.dto.CinemaDTO;
import com.springboot.pople.dto.MovieDTO;
import com.springboot.pople.dto.TheaterDTO;
import com.springboot.pople.entity.Cinema;
import com.springboot.pople.entity.Movie;
import com.springboot.pople.entity.Theater;
import com.springboot.pople.repository.CinemaRepository;
import com.springboot.pople.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final CinemaRepository cinemaRepository;

    private final ModelMapper modelMapper;

    public Long register(TheaterDTO theaterDTO){
        log.info(theaterDTO);
     Optional<Cinema> cinema =  cinemaRepository.findById(theaterDTO.getCinemaid());
        CinemaDTO cinemaDTO = modelMapper.map(cinema, CinemaDTO.class);

        theaterDTO.setCinemaid(cinemaDTO.getCinemaid());

        Theater theater = modelMapper.map(theaterDTO, Theater.class);

        log.info(theater);
        Long theaterid = theaterRepository.save(theater).getId();
        log.info(theaterid);

        return theaterid;
    }





}
