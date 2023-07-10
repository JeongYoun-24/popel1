package com.springboot.pople.service;

import com.springboot.pople.dto.UsersDTO;
import com.springboot.pople.entity.Users;
import com.springboot.pople.repository.UsersRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class usersServiceImpl implements UsersService{

    private ModelMapper modelMapper;

    private UsersRepository usersRepository;

    @Override
    public String register(UsersDTO usersDTO) {
        // dto-> entity로 데이터 복사
        Users board = modelMapper.map(usersDTO,Users.class);
        String user_id = usersRepository.save(board).getUser_id();

        return user_id;
    }

    @Override
    public UsersDTO readOne(String user_id) {
        Optional<Users> result = usersRepository.findById(user_id);
        Users users = result.orElseThrow();
        UsersDTO usersDTO = modelMapper.map(users,UsersDTO.class);

        return usersDTO;
    }

    @Override
    public void modify(UsersDTO usersDTO) {

        Optional<Users> result =  usersRepository.findById(usersDTO.getUser_id());
        Users board = result.orElseThrow();

        board.change(usersDTO.getUser_pwd(),usersDTO.getUser_name(),usersDTO.getUser_email());
        usersRepository.save(board);

    }

    @Override
    public void remove(String user_id) {
        usersRepository.deleteById(user_id);
    }
}
