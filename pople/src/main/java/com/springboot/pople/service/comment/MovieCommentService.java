package com.springboot.pople.service.comment;

import com.springboot.pople.dto.CommentDTO;
import com.springboot.pople.dto.CommentFormDTO;
import com.springboot.pople.dto.TheaterDTO;
import com.springboot.pople.dto.theater.TheaterFormDTO;
import com.springboot.pople.entity.Comment;
import com.springboot.pople.entity.Movie;
import com.springboot.pople.entity.Theater;
import com.springboot.pople.entity.Users;
import com.springboot.pople.repository.MovieCommentRepository;
import com.springboot.pople.repository.UsersRepository;
import com.springboot.pople.repository.movie.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class MovieCommentService {


    private final ModelMapper modelMapper;
    private final MovieCommentRepository movieCommentRepository;//dao
    private final MovieRepository movieRepository;
    private final UsersRepository usersRepository;

    public Long register(CommentDTO commentDTO) {
        // dto -> entity로 데이터 복사
      Movie movie = Movie.builder().movieid(commentDTO.getMovieid()).build();

       Users users =Users.builder().userid(commentDTO.getUsersid()).build();


        Comment comment = Comment.builder()
                .movie(movie)
                .users(users)
                .content(commentDTO.getContent())
                .star(commentDTO.getStar())
                .regDate(LocalDateTime.now())
                .build();


        Long id = movieCommentRepository.save(comment).getId();

        return id;
    }


    public CommentDTO readOne(Long id) {
        Optional<Comment> result = movieCommentRepository.findById(id);
        Comment board = result.orElseThrow();

        CommentDTO boardDto = modelMapper.map(board, CommentDTO.class);

        return boardDto;
    }


    public void modify(CommentDTO commentDTO) {

        Optional<Comment> result = movieCommentRepository.findById(commentDTO.getId());
        Comment board = result.orElseThrow();

//        board.change(boardDto.getTitle(), boardDto.getContent());
//        movieCommentRepository.save(board);

    }


    public void remove(Long id) {

        movieCommentRepository.deleteById(id);
    }

    public  List<CommentFormDTO> findList(Long movieid ,Pageable pageable){
     List<Comment> commentList = movieCommentRepository.findComment(movieid,pageable);
        log.info(""+commentList);
        List<CommentFormDTO> commentDTOList= new ArrayList<>();
        for(Comment comment : commentList){
            CommentFormDTO commentDTO = CommentFormDTO.builder()
                    .id(comment.getId())
                    .users(comment.getUsers())
                    .movie(comment.getMovie())
                    .content(comment.getContent())
                    .star(comment.getStar())
                    .regDate(comment.getRegDate())
                    .build();
            commentDTOList.add(commentDTO);

//            List<CommentDTO> theaterDTOList2 = new ArrayList<>();
//            for(CommentFormDTO formDTO : commentDTOList){
//                CommentDTO theaterListDTO2 = CommentDTO.builder()
//                        .id(formDTO.getId())
//                        .usersid(formDTO.getUsers())
//                        .theaterName(formDTO.getTheaterName())
//                        .build();// entity->dto 메서드호출
//                theaterDTOList2.add(theaterListDTO2);
//            }



//            CommentDTO theaterListDTO = CommentDTO.of(comment);// entity->dto 메서드호출
//            commentDTOList.add(theaterListDTO);
        }
        log.info("fsdfsdfsdfsd"+commentDTOList);

        return  commentDTOList;
    }





}
