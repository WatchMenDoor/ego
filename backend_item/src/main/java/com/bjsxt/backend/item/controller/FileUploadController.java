package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.FileUploadService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.backend.item.controller
 * @version: 1.0
 */
//负责图片上传
    @RestController("/file")
public class FileUploadController {

        @Autowired
        private FileUploadService fileUploadService;

        //实线图片上传的方法
    @PostMapping("/upload")
    public Result fileUpload(MultipartFile file){


        try {

            return this.fileUploadService.fileUpload(file);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
