package com.springboot.projectGT.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/*")
@RequiredArgsConstructor
public class MainController {



    @GetMapping(value = "test")
    public String main(){

        return "test/test";
    }





}
