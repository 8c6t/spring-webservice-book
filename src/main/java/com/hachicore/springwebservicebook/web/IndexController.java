package com.hachicore.springwebservicebook.web;

import com.hachicore.springwebservicebook.config.auth.LoginUser;
import com.hachicore.springwebservicebook.config.auth.dto.SessionAccount;
import com.hachicore.springwebservicebook.service.posts.PostsService;
import com.hachicore.springwebservicebook.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionAccount account) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (account != null) {
            model.addAttribute("userName", account.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
