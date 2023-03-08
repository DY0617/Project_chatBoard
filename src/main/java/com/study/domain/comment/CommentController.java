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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/board")
public class CommentController {

    private CommentService commentService;
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);


    @RequestMapping(value="/{board_id}/comment.read", method=RequestMethod.GET)
    public Map<String, Object> readCommentList(@PathVariable("board_id") Long board_id) throws Exception{
        logger.info("글번호: " + board_id);

        Map<String, Object> map = new HashMap<String, Object>();
        List<CommentResponse> cvo = commentService.findAllComment(board_id);
        int comment_count = commentService.count(board_id);

        map.put("comment", cvo);
        map.put("comment_count", comment_count);
        return map;
    }

    @RequestMapping(value="/comment.insert", method=RequestMethod.POST)
    public String insertComments(@RequestBody CommentRequest comment) throws Exception {
        logger.info("글내용: " + comment.getContent());
        comment.setContent(comment.getContent().replace("\r\n", "<br>"));
        commentService.saveComment(comment);
        return "success";
    }

    @RequestMapping(value="/{board_id}/comment.delete/{comments_id}", method=RequestMethod.DELETE)
    public String deleteComments(@PathVariable("board_id") int board_id, @PathVariable("comments_id") Long comments_id) throws Exception{
        commentService.deleteComment(comments_id);
        return "success";
    }



}