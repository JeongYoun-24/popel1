package com.springboot.pople.repository;

import com.springboot.pople.entity.BoardImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardImgRepository extends JpaRepository<BoardImg,Long> {

    List<BoardImg> findByBoardIdOrderByIdAsc(Long boardId);

    // 주문 상품 대표이미지 조회
    BoardImg findByBoardIdAndRepImgYn(Long boardId, String repimgYn);


}
