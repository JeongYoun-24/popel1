package com.springboot.pople.service;

import com.springboot.pople.dto.UsersDTO;
import com.springboot.pople.entity.Users;
import com.springboot.pople.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class usersServiceImpl implements UsersService{


    private final ModelMapper modelMapper;

    private final UsersRepository usersRepository;




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

    @Override
    public UsersDTO findByLoginId(String user_id, String user_pwd) {
        return null;
    }

    @Override
    public List<Users> allList() {
        List<Users> itemList = usersRepository.findAll();


        return itemList;
    }

    @Override
    public UsersDTO loginId(String user_email) { // 이메일로 비밀번호 찾기
        Optional<Users> result = usersRepository.findById(user_email);
        Users users = result.orElseThrow();
        UsersDTO usersDTO = modelMapper.map(users,UsersDTO.class);

        return null;
    }

    @Override
    public UsersDTO loginPwd(String user_id, String user_email) { //아이디와 이메일로 비밀번호 찾기
        Optional<Users> result = usersRepository.findById(user_id);
        Users users = result.orElseThrow();
        UsersDTO usersDTO = modelMapper.map(users,UsersDTO.class);

        return null;
    }




}
