package com.project.bank.service.Member;

import com.project.bank.entity.Member;
import com.project.bank.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
//public class MemberService implements UserDetailsService {
public class LoginService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        // 서버에서 validate적용
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        Member findMember2 = memberRepository.findByMemberId(member.getMemberId());
        
        // 이미 가입된 회원인 경우 예외 발생
        if (findMember != null){
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
        if (findMember2 != null){
            throw new IllegalStateException("이미 가입된 아이디입니다.");
        }
        
        
        
        

    }

    /*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new UsernameNotFoundException(email);
        }
        // DB로 회원 정보
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
    */

}

/*
스프링 시큐리티를 이용하여 로그인/로그아웃 기능 구현하기

UserDetailsService인터페이스: DB에서 회원정보를 가져오는 역할
lodadUserByUsername():
 회원정보를 조회하여 사용자의 정보와 권한을 갖는 UserDetails인터페이스 반환

UserDetail: 직접구현, 시큐리티에서 제공하는 User클래스를 사용




 */
