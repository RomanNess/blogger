package biz.cosee.workshop.blogger.controller;


import biz.cosee.workshop.blogger.dto.ArticleDto;
import biz.cosee.workshop.blogger.service.ArticleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.time.Instant;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SqlGroup({
        @Sql(scripts = {"classpath:sql/articles.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = {"classpath:sql/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
class ThymeleafControllerMockMvcTest extends AbstractMockMvcTest {

    @SpyBean
    private ArticleService articleService;

    private static final Instant NOW = Instant.now();

    @BeforeEach
    void initMocks() {
        doReturn(NOW).when(articleService).provideNow();
    }

    @Test
    @WithMockUser(value = "test-user")
    void getArticles() throws Exception {
        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attribute("articles", hasSize(2)));
    }

    @Test
    void getArticlesWhenNotLoggedIn() throws Exception {
        mockMvc.perform(get("/blog"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(value = "test-user")
    public void postArticle() throws Exception {
        mockMvc.perform(post("/blog")
                        .param("title", "The title")
                        .param("content", "The content.")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/blog"));

        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attribute("articles", hasSize(3)))
                .andExpect(model().attribute("articles", hasItem(equalTo(new ArticleDto(1L, "The title", "The content.", NOW.toString())))));
    }

    @Test
    @WithMockUser(value = "test-user")
    public void deleteArticle() throws Exception {
        mockMvc.perform(post("/blog/100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/blog"));

        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attribute("articles", hasSize(1)));
    }
}
