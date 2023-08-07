package com.springboot.pople.controller.comment;

import com.springboot.pople.dto.CommentDTO;
import com.springboot.pople.dto.MovieDTO;
import com.springboot.pople.entity.Users;
import com.springboot.pople.repository.UsersRepository;
import com.springboot.pople.service.comment.MovieCommentService;
import com.springboot.pople.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class commentController {
    private final MovieService movieService;
    private final MovieCommentService movieCommentService;
    private final UsersRepository usersRepository;

    @GetMapping(value ="/form/{movieName}")
    public String getComment(@PathVariable("movieName") String movieName, Model model){
        log.info("댓글 쓰기 페이지 요청 ");

        MovieDTO movieDTO = movieService.nameOne(movieName);
        log.info(movieDTO);
        model.addAttribute("movie",movieDTO);


        return "movie/commentForm";
    }

    @PostMapping (value ="/form")
    public String getComment(HttpServletRequest req, Model model,RedirectAttributes redirectAttributes){
        log.info("댓글 쓰기 페이지 요청 ");
        String movieid =req.getParameter("movieid");
        String movieName =req.getParameter("movieName");
        String userName =req.getParameter("userName");
        String star =req.getParameter("star");
        String content =req.getParameter("content");

        int star1 = Integer.parseInt(star);
        Long movieid1 = Long.parseLong(movieid);

       Users users =  usersRepository.findByName(userName);

        CommentDTO commentDTO = CommentDTO.builder()
                .content(content)
                .star(star1)
                .movieid(movieid1)
                .usersid(users.getUserid())
                .build();


//        if (bindingResult.hasErrors()){
//            log.info("has errors ...");
//
//            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
//            return "redirect:/comment/form";
//        }

        Long id =  movieCommentService.register(commentDTO);

        redirectAttributes.addFlashAttribute("result", "("+movieName+")"+"영화에"+id+"번 댓글 작성완료.");
        return "redirect:/main";
    }




}
