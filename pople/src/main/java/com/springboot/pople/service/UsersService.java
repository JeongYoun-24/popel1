package com.springboot.pople.service;

import com.springboot.pople.dto.UsersDTO;

public interface UsersService {

    public String register(UsersDTO usersDTO);
    public UsersDTO readOne(String user_id);
    public void modify(UsersDTO usersDTO);
    public void remove(String user_id);

}
