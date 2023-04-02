package com.budgetchecker.domain.comment;

import java.util.List;


import com.budgetchecker.common.dto.UserDto;
import com.budgetchecker.common.security.auth.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void insertComments(@PathVariable(value = "boardId") Long boardId,@RequestBody CommentRequest comment,@LoginUser UserDto.Response user) throws Exception {
        comment.setId(comment.getId());
        comment.setPostId(boardId);
        comment.setWriter(user.getNickname());
        comment.setUserId(user.getId());
        comment.setContent(comment.getContent().replace("\r\n", "<br>"));
        commentService.saveComment(comment);
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable(value = "commentId") Long commentId,@RequestBody CommentRequest comment,@LoginUser UserDto.Response user) throws Exception {
        comment.setWriter(user.getNickname());
        comment.setId(commentId);
        commentService.updateComment(comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComments(@PathVariable(value = "commentId") Long commentId) throws Exception{
        commentService.deleteComment(commentId);
    }



}