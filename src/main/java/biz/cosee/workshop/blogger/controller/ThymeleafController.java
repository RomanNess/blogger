package biz.cosee.workshop.blogger.controller;

import biz.cosee.workshop.blogger.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class ThymeleafController {

    @GetMapping("/blog")
    public String main(Model model) {
        model.addAttribute("user", "John Doe");
        model.addAttribute("articles", Arrays.asList(
                ArticleDto.builder().id(0L).title("title1").content("content1").build(),
                ArticleDto.builder().id(0L).title("title2").content("content2").build()
        ));

        return "blog";
    }
}
