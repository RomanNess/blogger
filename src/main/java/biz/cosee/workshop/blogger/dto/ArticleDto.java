package biz.cosee.workshop.blogger.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ArticleDto {

    private final Long id;

    private final String title;
    private final String content;

    @JsonCreator
    @Builder
    public ArticleDto(@JsonProperty("id") Long id, @JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
