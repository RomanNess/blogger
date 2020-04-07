package biz.cosee.workshop.blogger.controller;

import biz.cosee.workshop.blogger.dto.ArticleDto;
import biz.cosee.workshop.blogger.dto.TagDto;
import biz.cosee.workshop.blogger.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Secured("ROLE_USER")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlogController {

    //TODO when redirecting keep active tag and not only redirect to /blog without tag param.

    private final ArticleService articleService;
    private final List<TagDto> mockTags = List.of(
            new TagDto("foo"),
            new TagDto("bar"),
            new TagDto("hotshit"),
            new TagDto("trending"),
            new TagDto("canyoureadthis"),
            new TagDto("42"),
            new TagDto("whathaveyoudone"),
            new TagDto("neverregret"),
            new TagDto("canttouchdis"),
            new TagDto("livelovelaugh"),
            new TagDto("weed")
            );

    @GetMapping("/")
    public String blog() {
        return "redirect:/blog";
    }

    @GetMapping("/blog")
    public String blog(@RequestParam(value = "tag", required = false) String tagName, Model model) {
        model.addAttribute("articles", articleService.getAll());
        model.addAttribute("newArticle", new ArticleDto());    // initialize attribute for post
        model.addAttribute("allTags", mockTags);
        model.addAttribute("activeTag", tagName);
        return "blog";
    }

    @PostMapping("/blog/create")
    public String newArticle(@ModelAttribute("article") ArticleDto articleDto, Model model) {

        articleService.create(articleDto);
        return "redirect:/blog";
    }

    @GetMapping("/blog/update/{id}")
    public String updateArticleView(@PathVariable("id") Long articleId, Model model) {

        ArticleDto articleDto = articleService.get(articleId);
        model.addAttribute("article", articleDto);
        return "editarticle";
    }

    @PostMapping("/blog/update/{id}")
    public String updateArticle(@PathVariable("id") Long articleId, @ModelAttribute("article") ArticleDto articleDto, Model model) {

        articleService.update(articleId, articleDto);
        return "redirect:/blog";
    }

    @PostMapping("/blog/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long articleId, Model model) {

        articleService.delete(articleId);
        return "redirect:/blog";
    }
}
