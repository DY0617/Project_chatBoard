package com.study.domain.comment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping("/{boardId}")
    public List<CommentResponse> getCommentList(@PathVariable(value = "boardId") Long boardId) throws Exception {
        List<CommentResponse> comments = commentService.findAllComment(boardId);
        return comments;
    }

    @PostMapping("/{boardId}")
    public void insertComments(@RequestBody CommentRequest comment) throws Exception {
        comment.setContent(comment.getContent().replace("\r\n", "<br>"));
        commentService.saveComment(comment);
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@RequestBody CommentRequest comment) throws Exception {
        commentService.updateComment(comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComments(@PathVariable("comments_id") Long comments_id) throws Exception{
        commentService.deleteComment(comments_id);
    }



}