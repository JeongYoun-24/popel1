package com.springboot.pople.repository;

import com.springboot.pople.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time,Long> {
}
