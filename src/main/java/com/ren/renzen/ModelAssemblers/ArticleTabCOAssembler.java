package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.Controllers.ArticleController;
import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.ResourceObjects.CommandObjects.ArticleDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
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
public class ArticleTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleDTOs.ArticleTabComponentCO> {

    final ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;

    public ArticleTabCOAssembler(ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO) {
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
    }

    @Override
    public ArticleDTOs.ArticleTabComponentCO assembleDomainToPublicModelView(ArticleDO articleDO) {

        ArticleDTOs.ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToPublicView(articleDO);
        articleTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
        return addLinksWithCurrentAuthentication(articleTabComponentCO);

    }

    @Override
    public ArticleDTOs.ArticleTabComponentCO assembleDomainToFullModelView(ArticleDO articleDO) {

        ArticleDTOs.ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToFullView(articleDO);
        articleTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);

        return addLinksWithCurrentAuthentication(articleTabComponentCO);
    }

    @Override
    public ArticleDTOs.ArticleTabComponentCO addLinksWithCurrentAuthentication(ArticleDTOs.ArticleTabComponentCO entity) {
        return entity
                .add(List.of(

                        linkTo(methodOn(ArticleController.ArticleEditorController.class).likeArticle(entity.getObjectId(), getAuth())).withRel("LikeArticle"),
                        linkTo(methodOn(ArticleController.ArticleEditorController.class).dislikeArticle(entity.getObjectId(), getAuth())).withRel("DislikeArticle"),
                        linkTo(methodOn(ArticleController.ArticleEditorController.class).deleteArticle(entity.getObjectId(), getAuth())).withRel("DeleteArticle"),


                        linkTo(methodOn(ArticleController.ArticleViewerController.class).getArticleStreamComponentCO(entity.getObjectId(), getAuth())).withRel("Stream_Version"),
                        linkTo(methodOn(ArticleController.ArticleViewerController.class).getArticleTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version")));

    }
}

