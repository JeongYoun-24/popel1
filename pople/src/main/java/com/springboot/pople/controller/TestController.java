package com.springboot.pople.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/users")
public class TestController {

    @GetMapping(value = "/test")
    public String test1(){


        return "views/test";
    }
    @GetMapping(value = "/layout")
    public String layout(){

        return "layout/layout";
    }

    @GetMapping(value = "/loginpage")
    public String loginpage(){

        return "users/login";
    }
    @GetMapping(value = "/join")
    public String joinpage(){


        return "users/join";
    }
    @RequestMapping(value = "/joindata.do",method = {RequestMethod.POST})
    public String joindata(){

        return "";
    }




    @ResponseBody
    @RequestMapping(value = "/login.do",method = {RequestMethod.POST})
    public String login(@RequestBody HashMap<String, Object> map, Model model,HttpServletResponse resp){
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

//        member = service.login(user_id);

//        String memberId = member.getUser_id();
//        String memberPwd = member.getUser_pwd();
//
//        System.out.println(member);




        /*if(memberId.equals(user_id)) {

            if(memberPwd.equals(user_pwd)) {

                isOk = 1;
                HttpSession session = req.getSession();
                session.setAttribute("loginInfo", member.getUser_id());

            }else {
                isOk=2;
                System.out.println("비밀번호 틀림");
            }


        }else {
            isOk= -1;
            System.out.println("아이디 정보 없음");

        }*/


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



}
