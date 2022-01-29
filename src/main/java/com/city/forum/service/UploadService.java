package com.city.forum.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * cityforum
 * 文件处理
 *
 * @author : chenDW
 * @date : 2021-10-10 19:42
 **/
public interface UploadService {
    /**
     * 文件上传
     */
    public String upload(MultipartFile file,String type);
}
