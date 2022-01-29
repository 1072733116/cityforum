package com.city.forum.controller.api;

import com.city.forum.data.Result;
import com.city.forum.exception.ApiException;
import com.city.forum.model.vo.Uservo;
import com.city.forum.service.UserEditService;
import com.city.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * cityforum
 * 修改个人信息接口
 *
 * @author : chenDW
 * @date : 2021-09-21 22:17
 **/
@RestController
public class EditPersonInfoController extends BaseController{

    @Autowired
    private UserEditService userEditService;
    /**
     * 修改个人昵称
     * @param userNickname 新的昵称
     * @return 修改后的名称
     */
    @GetMapping("/user/editNickname")
    public Result<String> editPersonNickname(@RequestParam String userNickname){
        return userEditService.editPersonNickname(userNickname);
    }

    /**
     * 修改个人性别
     * @param sex
     * @return
     */
    @GetMapping("/user/editSex")
    public Result<String> editPersonSex(@RequestParam(name = "sex") String sex){
        return userEditService.editPersonSex(sex);
    }

    /**
     * 修改个性签名
     * @param sign
     * @return
     */
    @GetMapping("/user/editSign")
    public Result<String> editPersonSign(@RequestParam(name = "sign")String sign){
        return userEditService.editPersonSign(sign);
    }

    /**
     *  修改用户头像
     */
    @PostMapping("/user/editImage")
    public Result<String> edidPersonImage(@RequestParam(name = "file") MultipartFile file,@RequestParam(name = "type")String type){
        Result<String> result = null;
        try{
            result = userEditService.editPersonImage(file,type);
        }catch (IOException e){
            throw new ApiException(e.getMessage());
        }

        return result;
    }
}
