package com.springboot.pople.controller;

import com.springboot.pople.dto.BoardDTO;
import com.springboot.pople.dto.BoardFormDTO;
import com.springboot.pople.dto.BoardImgDTO;
import com.springboot.pople.service.board.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "list")
    public String getBoard(Model model){
        log.info("공지사항 리스트 ㄱㄱ");


        return "board/board";
    }
    @GetMapping(value = "form")
    public String getBoardForm(Model model){
        log.info("공지사항 리스트 작성 ㄱㄱ");
        model.addAttribute("boardFormDTO", new BoardFormDTO());

        return "board/boardForm";
    }
    @PostMapping(value ="form")
    public String postBoardForm(@Valid BoardFormDTO boardFormDTO, BindingResult bindingResult, Model model,
                                @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList){
        log.info("공지사항 리스트 작성 ㄱㄱ");
        log.info(boardImgFileList);
        if (bindingResult.hasErrors()){

            return "board/boardForm";
        }
//        if(boardImgFileList.get(0).isEmpty() && boardFormDTO.getId() == null){
//            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
//            return "board/boardForm";
//        }
        try {
            log.info("dasdasdasd"+boardFormDTO);
            boardService.saveBoard(boardFormDTO, boardImgFileList);

        }catch (Exception e){
            model.addAttribute("errorMessage","공지사항 등록 중 에러가 발생하였습니다.");
            return "board/boardForm";
        }

//        redirect
        return "redirect:/main";
    }

    // 공지사항 조회
    @GetMapping(value = "/admin/find/{boardid}")
    public String itemDtl(@PathVariable("boardid") Long boardid, Model model){

        try{
            BoardFormDTO boardFormDTO = boardService.getItemDtl(boardid);
            model.addAttribute("boardFormDTO",boardFormDTO);
            log.info("==> itemformDTO: "+boardFormDTO.getBoairImgDTOList());

        }catch (EntityNotFoundException e){

            model.addAttribute("errorMessage","존재하지 않는 상품입니다.");
            model.addAttribute("boardFormDTO", new BoardFormDTO());

            return "board/boardForm";
        }

        return "board/boardForm";
    }

    // 공지사항 정보 수정
    @PostMapping(value="/admin/modify/{boardid}")
    public String itemUpdate(
            @Valid BoardFormDTO boardFormDTO,
            BindingResult bindingResult,
            @RequestParam("boardImgFile") List<MultipartFile> boardImgFileList,
            Model model){

        // 데이터 검증 확인
        if (bindingResult.hasErrors()){
            return "board/boardForm";
        }
        // 첨부파일 여부 체크
        if (boardImgFileList.get(0).isEmpty() && boardFormDTO.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "board/boardForm";
        }

        // 상품 수정 서비스 호출
        try{
            boardService.updateBoard(boardFormDTO, boardImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage","상품 수정 중 에러가 발생했습니다.");
            return "board/boardForm";
        }

        return "redirect:/main";
    }


    // 상품 관리 조회 - 관리자 페이지
//    @GetMapping(value={"/admin/items","/admin/items/{page}"})
//    public String itemMange(ItemSearchDTO itemSearchDTO,
//                            @PathVariable("page") Optional<Integer> page,
//                            Model model){
//
//        Pageable pageable = PageRequest.of(page.isPresent()? page.get(): 0, 5);
//        Page<Item> items = itemService.getAdminItemPage(itemSearchDTO, pageable);
//
//        model.addAttribute("items", items);
//        model.addAttribute("itemSearchDTO", itemSearchDTO);
//        model.addAttribute("maxPage",5);// 하단에 보여질 페이지번호 범위(페이지 블럭)
//
//        return "item/itemMng";
//    }

//    // 상품 상세 페이지
//    @GetMapping(value="/item/{boarId}")
//    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){
//        ItemFormDTO itemFormDTO = itemService.getItemDtl(itemId);
//
//        model.addAttribute("item",itemFormDTO);
//
//        log.info("=> 상품 상세페이지");
//        log.info("item => "+itemFormDTO.toString());
//        log.info("================");
//        return "item/itemDtl";
//    }

}
