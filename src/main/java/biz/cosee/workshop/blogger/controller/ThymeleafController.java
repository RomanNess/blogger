package biz.cosee.workshop.blogger.controller;

import biz.cosee.workshop.blogger.dto.ArticleDto;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.unbescape.html.HtmlEscape;

import javax.servlet.http.HttpServletRequest;

@Secured("ROLE_USER")
@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThymeleafController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String blog() {
        return "redirect:/blog";
    }

    @GetMapping("/blog")
    public String blog(Model model) {
        model.addAttribute("user", "John Doe");
        model.addAttribute("articles", articleService.getAll());
        model.addAttribute("article", new ArticleDto());    // initialize attribute for post
        return "blog";
    }

    @PostMapping("/blog")
    public String newArticle(@ModelAttribute("article") ArticleDto articleDto, Model model) {

        articleService.create(articleDto);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}")
    public String deleteArticle(@PathVariable("id") Long articleId, Model model) {

        articleService.delete(articleId);
        return "redirect:/blog";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    @GetMapping("/403")
    public String forbidden() {
        return "403";
    }
}
