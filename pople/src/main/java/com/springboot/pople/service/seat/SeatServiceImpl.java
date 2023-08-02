package com.springboot.pople.service.seat;

import com.springboot.pople.dto.CinemaDTO;
import com.springboot.pople.dto.SeatDTO;
import com.springboot.pople.entity.Cinema;
import com.springboot.pople.entity.Movie;
import com.springboot.pople.entity.Seat;
import com.springboot.pople.repository.SeatRepository;
import com.springboot.pople.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final ModelMapper modelMapper;
    private final SeatRepository seatRepository;//dao
    @Override
    public Long register(SeatDTO seatDTO) {
        Seat seat = modelMapper.map(seatDTO, Seat.class);
        Long seatid = seatRepository.save(seat).getSeatid();


        return seatid;

    }

    @Override
    public SeatDTO readOne(Long seatid) {
        return null;
    }

    @Override
    public void modify(SeatDTO seatDTO) {

    }

    @Override
    public void remove(Long seatid) {

    }

    @Override
    public List<SeatDTO> AllList() {
        List<Seat> seatList = seatRepository.findAll();
        log.info(seatList);
        SeatDTO seatDTO = modelMapper.map(seatList,SeatDTO.class);
        log.info(seatDTO);
        List<SeatDTO> seatDTOList = new ArrayList<>();
        log.info(seatDTOList);

        for(Seat seat: seatList){
            SeatDTO seatListDTO = SeatDTO.of(seat);// entity->dto 메서드호출
            seatDTOList.add(seatListDTO);
        }

        return seatDTOList;
    }
}
