package biz.cosee.workshop.blogger.controller;


import biz.cosee.workshop.blogger.dto.ArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
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

    @Test
    void getArticles() throws Exception {
        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attribute("articles", hasSize(2)));
    }

    @Test
    public void postArticle() throws Exception {
        this.mockMvc
                .perform(post("/blog")
                        .param("title", "The title")
                        .param("content", "The content.")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attribute("articles", hasSize(3)))
                .andExpect(model().attribute("articles", hasItem(equalTo(new ArticleDto(1L, "The title", "The content.")))));
    }
}
