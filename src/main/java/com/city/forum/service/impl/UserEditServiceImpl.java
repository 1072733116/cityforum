package com.city.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.data.Result;
import com.city.forum.enums.ResultCode;
import com.city.forum.exception.ApiException;
import com.city.forum.mapper.UserMapper;
import com.city.forum.model.entity.User;
import com.city.forum.service.UserEditService;
import com.city.forum.util.FileUtil;
import com.city.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * cityforum
 *
 * @author : chenDW
 * @date : 2021-09-22 13:30
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class,ApiException.class})
public class UserEditServiceImpl extends ServiceImpl<UserMapper, User> implements UserEditService {

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private FileUtil fileUtil;


    /**
     * 修改个人昵称
     *
     * @param userNickname 新的昵称
     * @return
     */
    @Override
    public Result<String> editPersonNickname(String userNickname) {
        User user = userUtil.getCurrentUser();
        user.setUserNickname(userNickname);
        int result = baseMapper.updateById(user);
        if (result != 0) {
            return new Result<>(user.getUserNickname());
        }
        return new Result<String>(ResultCode.FAILED, "修改失败");
    }

    /**
     * 修改性别
     *
     * @param sex 新的性别
     * @return
     */
    @Override
    public Result<String> editPersonSex(String sex) {
        User currentUser = userUtil.getCurrentUser();
        currentUser.setUserSex(sex);
        int result = baseMapper.updateById(currentUser);
        if (result != 0) {
            return new Result<>(currentUser.getUserSex());
        }
        return new Result<String>(ResultCode.FAILED, "修改失败");
    }

    /**
     * 修改个性签名
     *
     * @param sign
     * @return
     */
    @Override
    public Result<String> editPersonSign(String sign) {
        User currentUser = userUtil.getCurrentUser();
        currentUser.setUserSign(sign);
        int result = baseMapper.updateById(currentUser);
        if (result != 0) {
            return new Result<>(currentUser.getUserSign());
        }
        return new Result<String>(ResultCode.FAILED, "修改失败");
    }

    /**
     * 上传头像
     *
     * @param file 上传的文件
     * @return 文件资源的访问路径
     */
    @Override
    public Result<String> editPersonImage(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {

        User currentUser = userUtil.getCurrentUser();
        String fileAddress = fileUtil.getFileAddress(file,type);
        currentUser.setUserAvatar(fileAddress);
        int result = baseMapper.updateById(currentUser);
        if (result != 0) {
            return new Result<>(currentUser.getUserAvatar());
        }
        return new Result<>("上传失败");
    }
}
