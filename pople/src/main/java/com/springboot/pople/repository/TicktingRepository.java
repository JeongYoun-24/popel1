package com.springboot.pople.repository;

import com.springboot.pople.entity.Tickting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicktionRepository  extends JpaRepository<Tickting,Long> {
}
