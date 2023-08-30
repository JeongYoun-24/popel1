package com.project.bank.service.Bank;

import com.project.bank.dto.AccoutDTO;

public interface AccountService {


    public String register(AccoutDTO accoutDTO); // 계좌 생성
    public AccoutDTO readOne(String accountNumber);  // 계좌 조회
    public int modify(AccoutDTO accoutDTO ,String name );   // 계좌 잔액 입금

    public int transfer(AccoutDTO accoutDTO,String name); //  계좌 잔액 이체
    public int remove(String accountNumber);     // 계좌 삭제

    public int pwd(String password,String accountNumber);   // 계좌 비밀번호 확인


    
    
    
}
