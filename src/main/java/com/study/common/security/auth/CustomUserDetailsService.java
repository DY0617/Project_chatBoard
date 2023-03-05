package com.study.common.security.auth;

import com.study.common.dto.UserDto;
import com.study.domain.user.User;
import com.study.domain.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Security User Service
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    private final HttpSession session;

    /* username이 DB에 있는지 확인 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + username));

        session.setAttribute("user", new UserDto.Response(user));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new CustomUserDetails(user);
    }
}