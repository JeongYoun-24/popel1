package com.springboot.pople.restcontroller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.springboot.pople.dto.UsersDTO;
import com.springboot.pople.entity.Users;
import com.springboot.pople.service.UsersService;
import com.springboot.pople.sesstion.SessionConst;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.UUID;

@RestController
@Log4j2
public class LoginController {

    @Autowired
    private UsersService usersService;



    @PostMapping(value = "/login")
    public String login(@RequestBody HashMap<String, Object> map, Model model,HttpServletResponse resp,HttpServletRequest req){
        String user_id = (String) map.get("user_id");
        String user_pwd = (String) map.get("user_pwd");
        String login_auto = (String) map.get("auto");

        log.info(user_id);
        log.info(user_pwd);
        log.info(login_auto);
        boolean rememberMe = login_auto != null && login_auto.equals("on");
        if(rememberMe) {
            //랜덤 아이디 값 얻기
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);

            /*  service.updateUuid(user_id, uuid);*/

//            member.setUuid(uuid);

            //---------------------

            Cookie rememberCookie = new Cookie("remember-me", uuid);
            rememberCookie.setMaxAge(24*60*60);
            rememberCookie.setPath("/");

            resp.addCookie(rememberCookie);



        }

        String rt= "";
        int isOk=0;
        UsersDTO usersDTO = usersService.readOne(user_id);
//        List<Users> usersDTO = usersService.allList();


        String memberId = usersDTO.getUser_id();
        String memberPwd = usersDTO.getUser_pwd();
//        member = service.login(user_id);

//        String memberId = member.getUser_id();
//        String memberPwd = member.getUser_pwd();
//
//        System.out.println(member);




        if(memberId.equals(user_id)) {

            if(memberPwd.equals(user_pwd)) {

                isOk = 1;
                HttpSession session = req.getSession();
                session.setAttribute("loginInfo", usersDTO.getUser_id());

            }else {
                isOk=2;
                System.out.println("비밀번호 틀림");
            }


        }else {
            isOk= -1;
            System.out.println("아이디 정보 없음");

        }


        if(isOk ==1) {

            //pw.print("<script> alert('로그인성공');  location.href='/web01/boardlist/list.do'</script>");
            rt = "로그인성공" ;
            log.info("로그인성공");

        }else if(isOk == 2) {
            //pw.print("<script> alert('비밀번호 틀림');  location.href='/web01/login'</script>");

            rt = "비밀번호를 잘못입렸습니다." ;
            log.info("비밀번호 틀림");
        }else {
            //pw.print("<script> alert('로그인 실패');  location.href='/web01/login'</script>");


            rt = "아이디를 잘못입렸습니다." ;
        }



        return rt;
    }


//    @PostMapping(value = "login")
//    public String loginPOST(@RequestBody HashMap<String, Object> map, HttpServletRequest request, RedirectAttributes rttr){
//        log.info("Controller loginPOST");
//        String user_id = (String) map.get("user_id");
//        String user_pwd = (String) map.get("user_pwd");
//        String login_auto = (String) map.get("auto");
//
//        HttpSession session = request.getSession();
//
////        UsersDTO login = usersService.userLogin(usersDTO);
//        UsersDTO usersDTO = usersService.readOne(user_id);
//
//        String failMessage = "아이디 혹은 비밀번호가 잘못 되었습니다.";
//
//        if (usersDTO == null) {
//            rttr.addFlashAttribute("loginFail", failMessage);
//            return "redirect:/login";
//        }
//
//        session.setAttribute("loginInfo", usersDTO.getUser_id());
//        return "redirect:/main";
//    }




}
