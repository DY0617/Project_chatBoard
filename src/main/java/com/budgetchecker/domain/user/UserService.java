package com.budgetchecker.domain.user;


import com.budgetchecker.common.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder encoder;

    /* 회원가입 */
    @Transactional
    public void userJoin(UserDto.Request dto) {

        dto.setPassword(encoder.encode(dto.getPassword()));

        userMapper.save(dto.toEntity());
    }

    /* 회원가입 시, 유효성 검사 및 중복 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사, 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }


    @Transactional
    public void updateMember(UserDto.Request dto) {
        User user = userMapper.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        String encPassword = encoder.encode(dto.getPassword());
        user.modify(dto.getNickname(), encPassword);
        userMapper.userUpdate(user);
    }
}


