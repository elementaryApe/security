package com.herman.security.controller;

import com.herman.security.entity.FileInfo;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 文件上传
 *
 * @author hsh
 * @create 2018-11-16 14:47
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    private String folder="G:\\practiceCode\\security\\security\\security-demo\\src\\main\\java\\com\\herman\\security\\controller";


    @PostMapping
    public FileInfo upload(MultipartFile file)throws Exception{
        logger.info(file.getName());
        logger.info(file.getOriginalFilename());//原始文件名
        logger.info(String.valueOf(file.getSize()));
        File localFile = new File(folder, new Date().getTime() + ".txt");
        file.transferTo(localFile);
        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping(value = "{id}")
    public void downLoad(@PathVariable(value = "id")String id, HttpServletRequest request, HttpServletResponse response)throws Exception{
        try (
            FileInputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
            OutputStream outputStream = response.getOutputStream()){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }

    }
}
