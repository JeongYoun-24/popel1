package com.springboot.pople.service.movie;

import com.springboot.pople.entity.Movie;
import com.springboot.pople.entity.MovieImg;
import com.springboot.pople.repository.MovieImgRepository;
import com.springboot.pople.repository.movie.MovieRepository;
import com.springboot.pople.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MovieImgService {

//    @Value("${org.zerock.upload.path}")
//    private String itemImgLocation;
    @Value("${movieImgLocation}")
    private String movieImgLocation;

    private final MovieImgRepository movieImgRepository;
    private final FileService fileService;
    private final MovieRepository movieRepository;

    // 1. 상품 이미지 정보 등록 서비스
    public void saveMovieImg(MovieImg movieImg, MultipartFile movieImgFile) throws Exception{
        log.info("이미지값"+movieImg);
        log.info("이미지값2"+movieImgFile.toString());
        String oriImgName = movieImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(
                    movieImgLocation,
                    oriImgName, // 파일이름
                    movieImgFile.getBytes());

            imgUrl ="/images/movie/"+imgName;
        }

        // 상품 이미지 정보 저장
        movieImg.updateItemImg(oriImgName, imgName, imgUrl);
        log.info("이미지값3"+movieImg);
        movieImgRepository.save(movieImg);

      //  Movie movie = movieRepository.findById(movieImg.getMovie().getMovieid()).orElseThrow(EntityNotFoundException::new);
      //  movie.setMoviePoster(movieImg.getImgName());
      //  movieRepository.save(movie);

    }

    // 2. 상품 이미지 정보 수정 서비스
    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile ) throws Exception {

        if (!itemImgFile.isEmpty()) {// 첨부(상품이미지)파일이 있으면 처리

            // 등록된 상품이미지 정보 호출
            MovieImg savedItemImg = movieImgRepository
                    .findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            // 등록된(기존) 상품이미지 파일 삭제
            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(movieImgLocation + "/" + savedItemImg.getImgName());
            }

            // 변경된 상품이미지 업로드 및 상품이미지 entity 정보 변경(DB에는 반영안된 상태)
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(
                    movieImgLocation,
                    oriImgName,
                    itemImgFile.getBytes());

            String imgUrl = "/images/movie/movie/" + imgName;

            // 수정 폼으로 받은 상품이미지 정보 entity로 전달
            // entity가 변경되면 영속성 상태에서 자동으로 update쿼리 실행

            log.info("==> 상품이미지수정 서비스 => "+oriImgName+","+imgName);
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
            log.info("==> 상품이미지 entity변경후 => "+savedItemImg.getOriImgName());

        }
    }
}
