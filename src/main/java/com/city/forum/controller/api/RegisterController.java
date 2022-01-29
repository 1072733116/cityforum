package com.city.forum.controller.api;

import com.city.forum.model.entity.User;
import com.city.forum.model.param.LoginParam;
import com.city.forum.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * cityforum
 * 注册接口
 *
 * @author : chenDW
 * @date : 2021-09-14 14:03
 **/
@RestController
@Slf4j
public class RegisterController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String userRegister(@RequestBody @Validated LoginParam registerUser) {
        return userService.register(registerUser);
    }

}
