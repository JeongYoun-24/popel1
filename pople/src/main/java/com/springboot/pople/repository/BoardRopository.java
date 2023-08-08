package com.springboot.pople.repository;

import com.springboot.pople.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRopository extends JpaRepository<Board,Long> {


}
