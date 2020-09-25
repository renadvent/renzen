package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.Controllers.ArticleController;
import com.ren.renzen.Converters.ArticleDO_to_ArticleTabComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Test Assembler for Rest
 * adds a link to the response
 * <p>
 * use in future
 * /profile
 * links:
 * /ProfileStreamCO
 * /ProfileTabCO
 * <p>
 * /ListOfArticles
 * etc....
 * <p>
 * so use a single profile id, and get a list of possible responses.
 * in this case, possible responses are different CO's
 */
@Component
public class ArticleTabCOAssembler implements RepresentationModelAssembler<ArticleDO, ArticleTabComponentCO> {

    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;

    public ArticleTabCOAssembler(ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO) {
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
    }


    @Override
    public ArticleTabComponentCO toModel(ArticleDO articleDO) {

        ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convert(articleDO);

        return articleTabComponentCO

                .add(List.of(
                        linkTo(methodOn(ArticleController.class).getArticleStreamComponentCO(articleTabComponentCO.getObjectId())).withRel("Stream_Version"),
                        linkTo(methodOn(ArticleController.class).getArticleTabComponentCO(articleTabComponentCO.getObjectId())).withRel("Tab_Version")));
    }

}

