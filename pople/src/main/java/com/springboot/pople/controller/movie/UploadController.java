package com.springboot.pople.controller.movie;


import com.springboot.pople.dto.movie.UploadResultDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.server.reactive.HttpHandler;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
public class UploadController {

    @Value("${org.zerock.upload.path}")// application.properties 변수
    private String uploadPath;



//    public ResponseEntity<List<UploadResultDTO>>

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){

        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            log.info("fileNmae : "+srcFileName);

            File file = new File(uploadPath+File.separator+srcFileName);
            log.info("file :"+file);

            HttpHeaders handler = new HttpHeaders();

        handler.add("Content-Type", Files.probeContentType(file.toPath()));
        result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),handler,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean>removeFile(String fileName){
        String srcFileName =null;
        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath+File.separator+srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(),"s_"+file.getName());
            result = thumbnail.delete();

            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }




    @PostMapping("/upload")
    public ResponseEntity<List<UploadResultDTO>> uploadFile (MultipartFile[] uploadFiles){

        List<UploadResultDTO> resultDTOList = new ArrayList<>();


        for (MultipartFile uploadFile : uploadFiles){
            if(uploadFile.getContentType().startsWith("image")==false){
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            // 실제 파일 이름 전체 경로
            String orginName = uploadFile.getOriginalFilename();
            String fileName = orginName.substring(orginName.lastIndexOf("\\")+1);
            log.info("fileName = "+fileName);
            // 날짜 파일 생성
            String folderPath = makeFolder();
            // uuid
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름
            String saveName = uploadPath + File.separator+folderPath+File.separator+uuid+"_"+fileName;
            Path sevePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(sevePath);// 실제 이미지 저장
                // 섬네일 생성
                String thumbnailSaveName = uploadPath+File.separator+folderPath+File.separator+"s_"+uuid+"_"+fileName;
                // 섬네일 파일 이름 s_ 시작하게
                File thumbnailFile = new File(thumbnailSaveName);
                // 섬네일 생성
                 Thumbnailator.createThumbnail(sevePath.toFile(),thumbnailFile,400,400);

                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));
            }catch (IOException e) {
                e.printStackTrace();
            }


        }

        return new ResponseEntity<>(resultDTOList,HttpStatus.OK);
    }

    private String makeFolder(){
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/",File.separator);
       //make folder
       File uploadPathFolder = new File(uploadPath,folderPath);
       if(uploadPathFolder.exists() ==false){
           uploadPathFolder.mkdirs();
       }

       return folderPath;
    }



}
