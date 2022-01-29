package com.city.forum.controller.api;

import com.city.forum.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * cityforum
 * 点赞处理
 *
 * @author : chenDW
 * @date : 2021-10-01 09:21
 **/
@RestController
public class praiseController extends BaseController{

    @Autowired
    private PraiseService praiseService;

    /**
     * 对帖子点赞处理
     * @param postId 帖子id
     * @return
     */
    @GetMapping("/addPraise")
    public String addPraise(@RequestParam("postId")Integer postId){
        return praiseService.addPraise(postId);
    }

    /**
     * 取消帖子点赞
     * @param postId
     * @return
     */
    @GetMapping("/cancelPraise")
    public String cancelPraise(@RequestParam("postId")Integer postId){
        return praiseService.cancelPraise(postId);
    }
}
