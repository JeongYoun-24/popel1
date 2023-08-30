package com.project.bank.repository;

import com.project.bank.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Long> {


    History findByAccount_AccountNumber(String accountNumber);


}
