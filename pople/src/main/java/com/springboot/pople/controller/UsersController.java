package com.springboot.pople.controller;

import com.springboot.pople.dto.UsersDTO;
import com.springboot.pople.entity.Users;
import com.springboot.pople.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @Autowired
    private UsersService usersService;




    @GetMapping("/login")
    public String loginGET(String error, String logout){
        log.info("logout =  "+logout);
//        if(logout != null){
//            log.info("user logout");
//        }

        return "users/login";
    }
    @GetMapping("/logout")
    public String logoutGet(){

        return "redirect :/users/login?logout";
    }


    @GetMapping("/login/error")
    public String loginerror(Model model){

        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호가 잘못됏슴다");
        return "users/login";
    }

    @GetMapping(value = "/join")  // 회원 가입 페이지
    public String joinpage(Model model){
        model.addAttribute("memberDTO", new UsersDTO());

        return "users/join";
    }
    @RequestMapping(value = "/join",method = {RequestMethod.POST}) // 회원가입 데이터
    public String joindata(@Valid UsersDTO usersDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes ){
//        String users_id = req.getParameter("user_id");
//        String user_pwd = req.getParameter("user_pwd");
//        String user_name = req.getParameter("user_name");
//        String user_email = req.getParameter("user_email");
//        String phone = req.getParameter("phone");
//        String birthdate = req.getParameter("birthdate");
//        log.info(users_id);
//        log.info(user_pwd);
//        log.info(user_name);
//        log.info(user_email);
//        log.info(phone);
//        log.info(birthdate);
//
//
//
//        UsersDTO usersDTO = UsersDTO.builder()
//                .user_id(users_id)
//                .user_pwd(user_pwd)
//                .user_name(user_name)
//                .user_email(user_email)
//                .phone(phone)
//                .birthdate(birthdate)
//                .build();

//        String user_id = usersService.register(usersDTO);


//        if(bindingResult.hasErrors()){
//            log.info("board errors ...");
//            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors()); //리다이렉션 시 담을 값
//            return "redirect: /users/join";
//        }
//        Users savedMember =  loginService.saveMember(users);
//        String user_id = usersService.register(usersDTO);
//        redirectAttributes.addFlashAttribute("result",user_id);

        return "/users/login";  //sendRedirect

//        return "users/login";
    }





    @GetMapping("/modify")
    public String modifypage(Model model){



        return "users/modify";
    }

    @PostMapping("/modify")
    public String modifyDate(Model model){



        return "views/main";
    }

    @GetMapping("/loginId")
    public String loginId(Model model){



        return "users/loginId";
    }
//    @PostMapping("/users/loginId" produces="application/json;charset=UTF-8")
    @RequestMapping(value = "/users/loginId",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public String loginIdData(@RequestBody UsersDTO usersDTO, Model model,HttpServletResponse resp) {
        String rt  = "";
        String rt2 = "";
        log.info(usersDTO);




//
//        if(usersDTO.getUserId() !=null){
//            log.info("아이디값 들어옴");
//            UsersDTO usersDTO2 = usersService.readOne(usersDTO.getUserId());
//
//            log.info("비밀번호 = "+usersDTO2);
//            String memberPwd = usersDTO2.getUserPwd();
//
//            log.info("비밀번호 = "+memberPwd);
//            rt2  = memberPwd;
//
//
//            return rt2;
//        }




        rt  = "아이디";


        return rt;
    }





}
