package biz.cosee.workshop.blogger.service;

import biz.cosee.workshop.blogger.AbstractSpringContextTest;
import biz.cosee.workshop.blogger.dto.ArticleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleServiceTest extends AbstractSpringContextTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void insert() {
        ArticleDto dto = ArticleDto.builder().title("The title").content("The content.").build();

        ArticleDto articleDto = articleService.create(dto);
        assertThat(articleDto).isNotNull();
        assertThat(articleDto.getId()).isNotNull();
        assertThat(articleDto.getTitle()).isEqualTo("The title");
        assertThat(articleDto.getContent()).isEqualTo("The content.");
    }
}
