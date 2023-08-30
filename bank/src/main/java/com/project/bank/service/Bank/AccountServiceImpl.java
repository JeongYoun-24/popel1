package com.project.bank.service.Bank;

import com.project.bank.dto.AccoutDTO;
import com.project.bank.entity.Account;
import com.project.bank.entity.History;
import com.project.bank.entity.Member;
import com.project.bank.repository.AccountRepository;
import com.project.bank.repository.HistoryRepository;
import com.project.bank.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class AccountServiceImpl  implements AccountService{


    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;

    @Override
    public String register(AccoutDTO accoutDTO) { // 계좌 생성

        Member member = memberRepository.findByMemberId(accoutDTO.getMember().getMemberId());
        Account account = new Account();


//      계좌 생성 난수 (랜덤값)
        String acount3333 = "3333";
        String m = "-";
        int account2= (int)(Math.random() * 10)-1;
        int account3= (int)(Math.random() * 2000000)+1000000;

        String acount = acount3333+m+0+account2+m+account3;
        log.info("계좌 생성 완료"+acount);

        
        // 계좌 중복 확인
//        Account accountList = accountRepository.findById(acount).orElseThrow(EntityExistsException::new);
//        if(accountList.getAccountNumber() == acount){
//
//            int account4= (int)(Math.random() * 10)-1;
//            int account5= (int)(Math.random() * 2000000)+1000000;
//            acount = acount3333+m+0+account4+m+account5;
//        }

        // DTO -> entiry
        account.setAccountNumber(acount);
        account.setMember(member);
        account.setBalance(1);
        account.setPassword(accoutDTO.getPassword());
        account.setCerateDate(LocalDateTime.now());

        log.info("값 확인"+account.toString());
        // 계좌 생성후 저장 
        Account accounts = accountRepository.save(account);

        // 계좌 내역 초기값 생성
        History history = History.builder()
                .balance(account.getBalance())
                .updateDate(LocalDateTime.now())
                .memberName("관리자")
                .chk("입금")
                .account(account)
                .build();



        historyRepository.save(history);


        return accounts.getAccountNumber();
    }

    @Override
    public AccoutDTO readOne(String accountNumber) {

      Account account =accountRepository.findById(accountNumber).orElseThrow(EntityExistsException::new);
      log.info(account);
      AccoutDTO accoutDTO = AccoutDTO.builder()
              .accountNumber(account.getAccountNumber())
              .createDate(account.getCerateDate())
              .balance(account.getBalance())
              .password(account.getPassword())
              .member(account.getMember())
              .build();



        log.info(accoutDTO);


        return accoutDTO;
    }

    @Override
    public int modify(AccoutDTO accoutDTO,String name) {

        Account account = accountRepository.findById(accoutDTO.getAccountNumber()).orElseThrow(EntityExistsException::new);
        int balance =accoutDTO.getBalance()+account.getBalance();
        log.info("금액 합치기 "+balance);


        account.setBalance(balance);
        account.setCerateDate(LocalDateTime.now());

        log.info("계좌 값"+account);
        accountRepository.save(account);

        // 계좌 내역  생성 후 저장
//       History history = historyRepository.findByAccount_AccountNumber(account.getAccountNumber());
      //  history.setChk("입금");
      //  history.setMemberName(name);
      //  history.setAccount(account);
      //   history.setUpdateDate(LocalDateTime.now());

//        log.info(history.toString());

        History history1 = History.builder()
                .balance(accoutDTO.getBalance())
                .memberName(name)
                .account(account)
                .updateDate(LocalDateTime.now())
                .chk("입금")
                .build();
        log.info(history1.toString());

      History  history = historyRepository.save(history1);


        return history.getBalance();
    }

    @Override
    public int transfer(AccoutDTO accoutDTO, String name) {

        Account account = accountRepository.findById(accoutDTO.getAccountNumber()).orElseThrow(EntityExistsException::new);
        int balance =accoutDTO.getBalance()-account.getBalance();
        log.info("금액 빼기 "+balance);

        account.setBalance(balance);
        account.setCerateDate(LocalDateTime.now());
        log.info("계좌 값"+account);
        // 계좌 저장
        accountRepository.save(account);

        //이체 받는사람 값 저장





        return 0;
    }

    @Override
    public int remove(String accountNumber) {
        return 0;
    }

    @Override
    public int pwd(String password,String accountNumber) {
        log.info("비밀번호 확인 요청"+password);
    int result = 0;

     Account account= accountRepository.findById(accountNumber).orElseThrow(EntityExistsException::new);

     if(account.getPassword().equals(password)){
         result = 1;
         log.info("비밀번호 일치 ");
     }else {
         log.info("비밀번호 불일치 ");
         result = 0;
     }


        return result;
    }
}
