package com.city.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.city.forum.data.Result;
import com.city.forum.enums.ResultCode;
import com.city.forum.exception.ApiException;
import com.city.forum.mapper.UserMapper;
import com.city.forum.model.entity.User;
import com.city.forum.model.param.LoginParam;
import com.city.forum.model.vo.Uservo;
import com.city.forum.security.UserDetail;
import com.city.forum.service.AttentionService;
import com.city.forum.service.PraiseService;
import com.city.forum.service.ResourceService;
import com.city.forum.service.UserService;
import com.city.forum.util.JwtUtil;
import com.city.forum.util.UUIDUtil;
import com.city.forum.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * cityforum
 * 登录业务
 *
 * @author : chenDW
 * @date : 2021-09-12 21:31
 **/
@Service
@Transactional(rollbackFor = {RuntimeException.class, ApiException.class})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserUtil userUtil;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AttentionService attentionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PraiseService praiseService;

    /**
     * 登录业务
     *
     * @param param 用户参数
     * @return
     */
    @Override
    public Uservo login(LoginParam param) {
        System.out.println(param);
        //根据用户名查询出用户实体对象
        User currentUser = baseMapper.selectByUserAccount(param.getUserAccount());
        //若没有查到用户或者密码校验失败则抛出异常
        if (currentUser == null || !passwordEncoder.matches(param.getUserPassword(), currentUser.getUserPassword())) {
            throw new ApiException("帐号或密码错误");
        }

        Uservo userVO = new Uservo();
        userVO.setUservoId(currentUser.getUserId())
                .setUservoAccount(currentUser.getUserAccount())
                .setUservoAvatar(currentUser.getUserAvatar())
                .setUservoNickname(currentUser.getUserNickname())
                .setUservoSex(currentUser.getUserSex())
                .setUservoSign(currentUser.getUserSign())
                .setToken(jwtUtil.generate(currentUser.getUserAccount()))
                .setResourceIds(resourceService.getResoureIdsByUserId(currentUser.getUserId()))
                .setAttendtionIds(attentionService.getAttentionList(currentUser.getUserId()))
                .setPraisePostIds(praiseService.getPraisePostIds(currentUser.getUserId()));
        return userVO;
    }

    /**
     * 注册业务
     *
     * @param registerUser 注册的用户
     * @return
     */
    @Override
    public String register(LoginParam registerUser) {
        User currentUser = new User();
        currentUser.setUserAccount(registerUser.getUserAccount())
                .setUserPassword(passwordEncoder.encode(registerUser.getUserPassword()))
                .setUserAvatar("http://121.4.168.173:8081/pic/image/30580b12ecc34a10b4768edd66b1101b.jpg")
                .setUserNickname(UUIDUtil.getUUID().substring(0, 10));
        User user = baseMapper.selectByUserAccount(registerUser.getUserAccount());
        if (user != null) {
            throw new ApiException("用户名已存在");
        }
        baseMapper.insert(currentUser);
        return "注册成功";
    }

    /**
     * 修改用户密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public Result<String> updatePassword(String oldPassword, String newPassword) {
        User currentUser = userUtil.getCurrentUser();
        if (!passwordEncoder.matches(oldPassword, currentUser.getUserPassword())) {
            return new Result<>(ResultCode.FAILED, "密码错误");
        }
        currentUser.setUserPassword(passwordEncoder.encode(newPassword));
        int result = baseMapper.updateById(currentUser);
        if (result != 0) {
            return new Result<>("修改成功");
        }
        return new Result<>(ResultCode.FAILED, "修改失败");
    }

    /**
     * 忘记密码
     *
     * @param phone       手机号码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public String forgetPassword(String phone, String newPassword) {
        User user = baseMapper.selectByUserAccount(phone);
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        user.setUserPassword(passwordEncoder.encode(newPassword));
        int update = baseMapper.updateById(user);
        if (update != 0) {
            return "修改成功";
        }
        return "修改失败";
    }


    @Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
        // 先调用DAO层查询用户实体对象
        User user = baseMapper.selectByUserAccount(userAccount);
        //查询不到抛出异常
        if (user == null) {
            throw new ApiException("没有找到该用户");
        }
        //查询权限id
        Set<SimpleGrantedAuthority> authorities = resourceService.getResoureIdsByUserId(user.getUserId())
                .stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        //查询到了实体对象，返回我们自定义的UserDetail对象
        return new UserDetail(user, authorities);
    }
}
