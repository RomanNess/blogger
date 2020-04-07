package biz.cosee.workshop.blogger.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;

    private Instant lastUpdate;
}
