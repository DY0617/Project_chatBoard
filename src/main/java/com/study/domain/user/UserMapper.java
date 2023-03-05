package com.study.domain.user;


import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    User save(User toEntity);

    Optional<User> findById(Long id);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
