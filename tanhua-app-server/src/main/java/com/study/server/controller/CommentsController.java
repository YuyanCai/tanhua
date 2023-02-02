package com.study.server.controller;

import com.study.server.service.CommentsService;
import com.study.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 发布评论
     */
    @PostMapping
    public ResponseEntity saveComments(@RequestBody Map map) {
        String movementId = (String) map.get("movementId");
        String comment = (String) map.get("comment");
        commentsService.saveComments(movementId, comment);
        return ResponseEntity.ok(null);
    }

    //分页查询评论列表
    @GetMapping
    public ResponseEntity findComments(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer pagesize,
                                       String movementId) {
        PageResult pr = commentsService.findComments(movementId, page, pagesize);
        return ResponseEntity.ok(pr);
    }

}
