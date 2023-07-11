package com.springboot.pople.service;

import com.springboot.pople.dto.UsersDTO;
import com.springboot.pople.entity.Users;

import java.util.List;

public interface UsersService {

    public String register(UsersDTO usersDTO);
    public UsersDTO readOne(String user_id);
    public void modify(UsersDTO usersDTO);
    public void remove(String user_id);

    public List<Users> allList(); // 전체 리스트


}
