package com.city.forum.security;

import com.city.forum.data.Result;
import com.city.forum.enums.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * cityforum
 * 授权错误处理器
 *
 * @author : chenDW
 * @date : 2021-09-13 13:45
 **/
public class MyDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result<String> resultVO = new Result<>(ResultCode.FORBIDDEN, "没有相关权限");
        out.write(resultVO.toString());
        out.flush();
        out.close();
    }
}
