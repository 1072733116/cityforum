package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.city.forum.data.Result;
import com.city.forum.model.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * cityforum
 * 用户信息修改
 *
 * @author : chenDW
 * @date : 2021-09-22 13:29
 **/
public interface UserEditService extends IService<User> {
    public Result<String> editPersonNickname(String userNickname);

    public Result<String> editPersonSex(String sex);

    public Result<String> editPersonSign(String sign);

    public Result<String> editPersonImage(MultipartFile file,String type) throws IOException;
}
