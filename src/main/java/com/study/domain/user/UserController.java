package com.study.domain.user;

import com.study.common.dto.MessageDto;
import com.study.common.dto.UserDto;
import com.study.common.security.auth.LoginUser;
import com.study.common.validator.CustomValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private final CustomValidators.EmailValidator EmailValidator;
    private final CustomValidators.NicknameValidator NicknameValidator;
    private final CustomValidators.UsernameValidator UsernameValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(EmailValidator);
        binder.addValidators(NicknameValidator);
        binder.addValidators(UsernameValidator);
    }

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

    @GetMapping
    public String root() {
        return "redirect:/post/list.do";
    }

    @GetMapping("/auth/join")
    public String join() {
        return "user/join";
    }

    /* 회원가입 */

    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserDto.Request dto, Errors errors, Model model) {


        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("userDto", dto);

            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return "user/join";
        }
        userService.userJoin(dto);
        return "redirect:/auth/login";
    }


    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    /* 회원정보 수정 */
    @GetMapping("/modify")
    public String modify(@LoginUser UserDto.Response user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "user/modify";
    }

    @RequestMapping("/modify/update.do")
    public String modifyUpdate(@ModelAttribute UserDto.Request user,Model model){
        userService.updateMember(user);

        MessageDto message = new MessageDto("수정이 완료되었습니다. 로그아웃 합니다.", "/logout", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

}