package com.study.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    /**
     * 번호 (PK)
     */
    private Long idx;

    /**
     * 제목
     */
    private String title;

    /**
     * 내용
     */
    private String content;

    /**
     * 작성자
     */
    private String writer;

    /**
     * 조회 수
     */
    private int viewCnt;

    /**
     * 공지 여부
     */
    private String noticeYn;

    /**
     * 비밀 여부
     */
    private String secretYn;
}