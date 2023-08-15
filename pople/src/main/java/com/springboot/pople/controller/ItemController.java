package com.springboot.pople.controller;

import com.springboot.pople.dto.BoardDTO;
import com.springboot.pople.dto.item.ItemFormDTO;
import com.springboot.pople.dto.item.ItemSearchDTO;
import com.springboot.pople.entity.Item;
import com.springboot.pople.service.item.ItemService;
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
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value ="list")
    public String getItem(Model model){
        log.info("상품 주소 ㄱㄱ ");
        /*List<BoardDTO> boardDTO = boardService.AllList();
        log.info("1111"+boardDTO);*/

       /* model.addAttribute("board",boardDTO);*/

        return "item/itemTest";
    }


    @GetMapping(value="/admin/item/new")
    public String itemForm(Model model){
        log.info("===> Get /admin/item/new 요청");

        model.addAttribute("itemFormDTO", new ItemFormDTO());



        return "item/itemForm";
    }

    // 상품 정보 DB등록 처리
    @PostMapping(value="/admin/item/new")
    public String itemNew(@Valid ItemFormDTO itemFormDTO, BindingResult bindingResult, Model model,
            @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList   //"itemImgFile" 클라이언트로 넘겨받은 매개변수(files객체)
    ){
        log.info("===> Post /admin/item/new 요청");

        if (bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDTO, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage","상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/main";
    }

    // 상품 조회
    @GetMapping(value = "/admin/item/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){

        try{
            ItemFormDTO itemFormDTO = itemService.getItemDtl(itemId);
            model.addAttribute("itemFormDTO",itemFormDTO);
            log.info("==> itemformDTO: "+itemFormDTO.getItemImgDTOList());

        }catch (EntityNotFoundException e){

            model.addAttribute("errorMessage","존재하지 않는 상품입니다.");
            model.addAttribute("itemFormDTO", new ItemFormDTO());

            return "item/itemForm";
        }

        return "item/itemForm";
    }

    // 상품 정보 수정
    @PostMapping(value="/admin/item/{itemId}")
    public String itemUpdate(
            @Valid ItemFormDTO itemFormDTO,
            BindingResult bindingResult,
            @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
            Model model){

        // 데이터 검증 확인
        if (bindingResult.hasErrors()){
            return "item/itemForm";
        }
        // 첨부파일 여부 체크
        if (itemImgFileList.get(0).isEmpty() && itemFormDTO.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값입니다.");
            return "item/itemForm";
        }

        // 상품 수정 서비스 호출
        try{
            itemService.updateItem(itemFormDTO, itemImgFileList);
        }catch (Exception e){
            model.addAttribute("errorMessage","상품 수정 중 에러가 발생했습니다.");
            return "item/itemForm";
        }

        return "redirect:/";
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

    // 상품 상세 페이지
    @GetMapping(value="/list/{categoryid}")
    public String itemList(Model model, @PathVariable("categoryid") Long categoryid){



//        ItemFormDTO itemFormDTO = itemService.getItemDtl(categoryid);
//        model.addAttribute("item",itemFormDTO);


        return "item/itemDtl";
    }


    @GetMapping(value="/category/{categoryid}")
    public String itemDtl(Model model, @PathVariable("categoryid") Long categoryid){
//        List<ItemFormDTO> itemFormDTO = itemService.itemList(categoryid);
        List<ItemFormDTO> itemFormDTOList=  itemService.itemList(categoryid);
        log.info(itemFormDTOList);


        model.addAttribute("itemList",itemFormDTOList);
//
//        log.info("=> 상품 상세페이지");
//        log.info("item => "+itemFormDTO.toString());
//        log.info("================");


        return "item/itemList";
    }











}
