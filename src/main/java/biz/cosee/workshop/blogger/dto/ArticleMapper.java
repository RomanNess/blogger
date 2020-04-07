package biz.cosee.workshop.blogger.dto;

import biz.cosee.workshop.blogger.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    ArticleDto toDto(ArticleEntity entity);

    List<ArticleDto> toDto(Collection<ArticleEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    ArticleEntity toEntity(ArticleDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdate", source = "lastUpdate", qualifiedByName = "formatLastUpdated")
    void update(ArticleDto dto, @MappingTarget ArticleEntity existingArticleEntity);

    @Named("formatLastUpdated")
    default String formatLastUpdated(Instant instant) {
        return instant.toString();
    }
}
