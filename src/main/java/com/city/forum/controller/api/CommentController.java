package com.city.forum.controller.api;

import com.city.forum.model.vo.Commentvo;
import com.city.forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cityforum
 * 评论处理
 *
 * @author : chenDW
 * @date : 2021-10-03 09:16
 **/
@RestController
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    /**
     * 保存评论或回复
     */
    @PostMapping("/saveComment")
    public String saveComment(@RequestParam("postId") Integer postId,
                              @RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
                              @RequestParam("content") String content) {
        return commentService.saveComment(postId, parentId, content);
    }

    /**
     * 删除评论
     */
    @DeleteMapping("/deleteComment")
    public String deleteComment(@RequestParam("commentId") Integer commendId,@RequestParam("postId")Integer postId) {
        return commentService.deleteComment(commendId,postId);
    }

    /**
     * 获取评论
     */
    @GetMapping("/getComment/{postId}")
    public List<Commentvo> getComment(@PathVariable("postId")Integer postId){
        return commentService.getComment(postId);
    }
}
