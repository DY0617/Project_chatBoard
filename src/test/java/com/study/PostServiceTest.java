package com.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.study.domain.post.PostRequest;
import com.study.domain.post.PostResponse;
import com.study.domain.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void save() {
        PostRequest params = new PostRequest();
        params.setTitle("1번 게시글 제목");
        params.setContent("1번 게시글 내용");
        params.setWriter("테스터");
        params.setNoticeYn(false);
        System.out.println("생성된 게시글 ID : "+postService.savePost(params));
    }

    @Test
    void findById() {
        PostResponse post = postService.findPostById(1L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void update() {
        // 1. 게시글 수정
        PostRequest params = new PostRequest();
        params.setId(1L);
        params.setTitle("1번 게시글 제목 수정합니다.");
        params.setContent("1번 게시글 내용 수정합니다.");
        params.setWriter("도뎡이");
        params.setNoticeYn(true);
        postService.updatePost(params);

        // 2. 게시글 상세정보 조회
        PostResponse post = postService.findPostById(1L);
        try {
            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
            System.out.println(postJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void delete() {
        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postService.findAllPost().size() + "개입니다.");
        postService.deletePost(1L);
        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postService.findAllPost().size() + "개입니다.");
    }

}