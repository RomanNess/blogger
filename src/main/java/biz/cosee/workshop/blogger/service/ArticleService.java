package biz.cosee.workshop.blogger.service;

import biz.cosee.workshop.blogger.dto.ArticleDto;
import biz.cosee.workshop.blogger.dto.ArticleMapper;
import biz.cosee.workshop.blogger.entity.ArticleEntity;
import biz.cosee.workshop.blogger.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleMapper articleMapper;

    public List<ArticleDto> getAll() {
        List<ArticleEntity> articles = articleRepository.findAll();
        return articleMapper.toDto(articles);
    }

    public ArticleDto get(Long id) {
        Optional<ArticleEntity> article = articleRepository.findById(id);
        return articleMapper.toDto(article.orElseThrow(RuntimeException::new)); // todo: handle not found
    }

    public ArticleDto create(ArticleDto dto) {
        ArticleEntity articleEntity = articleMapper.toEntity(dto);
        ArticleEntity persistedArticleEntity = articleRepository.save(articleEntity);
        return articleMapper.toDto(persistedArticleEntity);
    }

    public ArticleDto update(Long id, ArticleDto dto) {

        ArticleEntity existingArticleEntity = articleRepository.getOne(id); // TODO: handle not found
        articleMapper.update(dto, existingArticleEntity);

        ArticleEntity persistedArticleEntity = articleRepository.save(existingArticleEntity);
        return articleMapper.toDto(persistedArticleEntity);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
