package com.city.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.city.forum.data.Result;
import com.city.forum.model.entity.User;
import com.city.forum.model.param.LoginParam;
import com.city.forum.model.vo.Uservo;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-12 21:30
 **/

public interface UserService extends IService<User> {
    /**
     * 登录
     * @param param 用户参数
     * @return 登录成功返回vo对象
     */
    public Uservo login(LoginParam param);

    /**
     * 用户注册
     * @param registerUser 注册的用户
     * @return 操作信息
     */
    public String register(LoginParam registerUser);

    public Result<String> updatePassword(String oldPassword, String newPassword);

    public String forgetPassword(String phone,String newPassword);
}
