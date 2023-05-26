package com.study.domain.post;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.dto.UserDto;
import com.study.common.security.auth.LoginUser;
import com.study.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;



    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }


    // 게시글 작성 페이지
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, Model model,@LoginUser UserDto.Response user) {
        if (id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "post/write";
    }

    // 게시글 리스트 페이지
    @GetMapping("/post/list.do")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model,@LoginUser UserDto.Response user) {
        PagingResponse<PostResponse> response = postService.findAllPost(params);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("response", response);
        return "post/list";
    }

    // 게시글 리스트 페이지
    @GetMapping("/post/list1.do")
    public String openPostCate1List(@ModelAttribute("params") final SearchDto params, Model model,@LoginUser UserDto.Response user) {
        PagingResponse<PostResponse> response = postService.findCate1Post(params);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("response", response);
        return "post/list";
    }

    // 게시글 리스트 페이지
    @GetMapping("/post/list2.do")
    public String openPostCate2List(@ModelAttribute("params") final SearchDto params, Model model,@LoginUser UserDto.Response user) {
        PagingResponse<PostResponse> response = postService.findCate2Post(params);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("response", response);
        return "post/list";
    }

    @GetMapping("/post/listMy.do")
    public String openMyPostList(@ModelAttribute("params") final SearchDto params, Model model,@LoginUser UserDto.Response user) {
        PagingResponse<PostResponse> response = postService.findMyPost(params,user);
        if (user != null) {
            model.addAttribute("user", user);
        }
        model.addAttribute("response", response);
        return "post/list";
    }

    // 게시글 상세 페이지
    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam final Long id, Model model, @LoginUser UserDto.Response user, HttpServletRequest request, HttpServletResponse response) {
        PostResponse post = postService.findPostById(id);

        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("["+ id.toString() +"]")) {
                postService.updateView(id);
                oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            postService.updateView(id);
            Cookie newCookie = new Cookie("postView", "[" + id + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(newCookie);
        }

        model.addAttribute("post", post);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "post/view";
    }

    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final PostRequest params, Model model,@LoginUser UserDto.Response user) {
        postService.savePost(params);
        if (user != null) {
            model.addAttribute("user", user);
        }
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final PostRequest params, Model model,@LoginUser UserDto.Response user) {
        postService.updatePost(params);
        if (user != null) {
            model.addAttribute("user", user);
        }
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id, final SearchDto queryParams, Model model,@LoginUser UserDto.Response user) {
        postService.deletePost(id);
        if (user != null) {
            model.addAttribute("user", user);
        }
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    // 쿼리 스트링 파라미터를 Map에 담아 반환한다.
    private Map<String, Object> queryParamsToMap(final SearchDto queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }


}
