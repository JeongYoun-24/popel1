package com.springboot.pople.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/*")
public class MainController{




        @GetMapping(value = "/main")
        public String main(){


            return "views/main";
        }


}
