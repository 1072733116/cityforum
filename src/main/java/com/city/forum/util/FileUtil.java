package com.city.forum.util;

import com.city.forum.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * cityforum
 * 生成文件名
 *
 * @author : chenDW
 * @date : 2021-09-22 22:15
 **/
@Component
public class FileUtil {

    @Value("${file.filePath}")
    private String uploadFolder;

    /**
     * 获取文件名后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtil.getUUID() + FileUtil.getSuffix(fileOriginName);
    }

    public String getFileAddress(MultipartFile file, String type) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getFileAddress(file, type, request);
    }

    /**
     * 上传文件处理
     *
     * @param file    上传的文件
     * @param type    文件类型
     * @param request
     * @return
     */
    public String getFileAddress(MultipartFile file, String type, HttpServletRequest request) {
        if (file.isEmpty()) {
            throw new ApiException("上传的文件为空");
        }
        //获取文件名
        String fileName = file.getOriginalFilename();
        //生成随机文件名
        String newFileName = FileUtil.getFileName(fileName);
        //上传文件路径
        String filePath = "";

        File dir = new File(uploadFolder + type);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        filePath = uploadFolder + "/" + type + File.separator + newFileName;
        //所上传的文件路径
        File localFile = new File(filePath);

        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            throw new ApiException(e.toString());
        }
        //保存完毕，返回保存地址
        String inventAdress = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                "/pic/" + type + "/" + newFileName;

        //数据库保存的地址
        return inventAdress;
    }
}
