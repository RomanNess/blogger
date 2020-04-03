package biz.cosee.workshop.blogger.repository;

import biz.cosee.workshop.blogger.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

}
