package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleStreamComponentCO;
import com.ren.renzen.Controllers.ArticleController;
import com.ren.renzen.Converters.ArticleDO_to_ArticleStreamComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleStreamCOAssembler implements RepresentationModelAssembler<ArticleDO, ArticleStreamComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    public ArticleStreamCOAssembler(ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
    }

    @Override
    public ArticleStreamComponentCO toModel(ArticleDO entity) {

        ArticleStreamComponentCO articleStreamComponentCO = articleDO_to_articleStreamComponentCO.convert(entity);

        return articleStreamComponentCO
                .add(List.of(
                        linkTo(methodOn(ArticleController.class).getArticleStreamComponentCO(articleStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                        linkTo(methodOn(ArticleController.class).getArticleTabComponentCO(articleStreamComponentCO.getObjectId())).withRel("Tab_Version")));
    }
}
