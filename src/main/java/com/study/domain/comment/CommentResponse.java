package com.study.domain.comment;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {

    private Long id;                       // 댓글 번호 (PK)
    private Long postId;                   // 게시글 번호 (FK)
    private String content;                // 내용
    private String writer;                 // 작성자
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시
    private Long userId;
    private Integer parent;                 //대댓글
}