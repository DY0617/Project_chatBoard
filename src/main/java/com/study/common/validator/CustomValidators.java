package com.study.common.validator;

import com.study.common.dto.UserDto;
import com.study.domain.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 중복 확인 유효성 검증을 위한 커스텀 Validator 클래스
 */
@RequiredArgsConstructor
@Component
public class CustomValidators {

    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<UserDto.Request> {
        private final UserMapper usermapper;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if (usermapper.existsByEmail(dto.toEntity().getEmail())) {
                errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class NicknameValidator extends AbstractValidator<UserDto.Request> {
        private final UserMapper userMapper;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if (userMapper.existsByNickname(dto.toEntity().getNickname())) {
                errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class UsernameValidator extends AbstractValidator<UserDto.Request> {
        private final UserMapper userMapper;

        @Override
        protected void doValidate(UserDto.Request dto, Errors errors) {
            if (userMapper.existsByUsername(dto.toEntity().getUsername())) {
                errors.rejectValue("username", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
            }
        }
    }
}