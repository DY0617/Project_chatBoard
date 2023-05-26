package com.study.domain.post;

import com.study.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     */
    void save(PostRequest params);

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    PostResponse findById(Long id);



    /**
     * 게시글 수정
     * @param params - 게시글 정보
     */
    void update(PostRequest params);

    /**
     * 게시글 삭제
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return 게시글 리스트
     */
    List<PostResponse> findAll(SearchDto params);

    List<PostResponse> findMyPost(HashMap<String, Object> map);

    /**
     * 게시글 수 카운팅
     * @param params - search conditions
     * @return 게시글 수
     */
    int count(SearchDto params);

    int count1(SearchDto params);

    int count2(SearchDto params);

    int countMyPost(Long id);

    int updateView(Long id);

    List<PostResponse> findCate1(SearchDto params);

    List<PostResponse> findCate2(SearchDto params);
}
