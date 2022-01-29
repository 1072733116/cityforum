package com.city.forum.controller.api;

import com.city.forum.data.Result;
import com.city.forum.model.entity.User;
import com.city.forum.service.UserService;
import com.city.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-22 13:50
 **/
@RestController
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 修改用户密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @PostMapping("/user/updatePassword")
    public Result<String> updatePassword(@RequestParam(name = "oldPassword")String oldPassword,@RequestParam(name = "newPassword")String newPassword){
        return userService.updatePassword(oldPassword,newPassword);
    }

    @PostMapping("/forgetPassword")
    public String forgetPassword(@RequestParam("phone")String phone,@RequestParam(name = "newPassword")String newPassword){
        return userService.forgetPassword(phone,newPassword);
    }
}
