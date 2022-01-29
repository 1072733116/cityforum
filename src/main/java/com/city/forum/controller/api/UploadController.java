package com.city.forum.controller.api;

import com.city.forum.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-10 19:39
 **/
@RestController
public class UploadController extends BaseController{

    @Autowired
    private UploadService uploadService;
    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file,@RequestParam("type")String type){
        return uploadService.upload(file,type);
    }
}
