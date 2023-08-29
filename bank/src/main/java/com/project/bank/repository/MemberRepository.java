package com.project.bank.repository;

import com.project.bank.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member,Long> {




}
