package com.city.forum.service.impl;

import com.city.forum.service.UploadService;
import com.city.forum.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-10-10 19:43
 **/
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FileUtil fileUtil;
    /**
     * 文件上传处理
     * @param file
     * @param type
     * @return
     */
    @Override
    public String upload(MultipartFile file, String type) {
        return fileUtil.getFileAddress(file,type);
    }
}
