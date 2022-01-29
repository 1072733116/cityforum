package com.city.forum.controller.api;

import com.city.forum.model.param.LoginParam;
import com.city.forum.model.vo.Uservo;
import com.city.forum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * cityforum
 * 登录接口
 *
 * @author : chenDW
 * @date : 2021-09-12 21:24
 **/
@RestController
@Slf4j
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Uservo login(@RequestBody @Validated LoginParam user){
        return userService.login(user);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
