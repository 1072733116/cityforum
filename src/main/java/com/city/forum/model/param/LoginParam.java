package com.city.forum.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * cityforum
 * 登录接收参数对象
 *
 * @author : chenDW
 * @date : 2021-09-12 21:28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam implements Serializable {
    /**
     * 登录帐号
     */
    @NotBlank(message = "账号不能为空")
    @Length(min = 6,max = 11,message = "请输入正确的手机号码")
    @JsonProperty(value = "userAccount")
    private String userAccount;
    /**
     * 登录密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6,max = 13,message = "密码长度为6-13位")
    @JsonProperty(value = "userPassword")
    private String userPassword;
}
