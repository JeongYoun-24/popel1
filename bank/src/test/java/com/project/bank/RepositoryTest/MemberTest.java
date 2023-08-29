package com.project.bank.RepositoryTest;

import com.project.bank.dto.MemberDTO;
import com.project.bank.entity.Member;
import com.project.bank.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Log4j2
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("회원가입 테스트")
    public void createMember(){
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId("aaa")
                .pswd("1234")
                .build();
    Member member = new Member();
        member.setMemberId(memberDTO.getMemberId());
        member.setMemberId(memberDTO.getMemberId());
    log.info(member);


            memberRepository.save(member);


//        // dto -> entity, 암호화 적용

    }






}
