package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.Controllers.ArticleEditorController;
import com.ren.renzen.Controllers.ArticleViewerController;
import com.ren.renzen.Converters.ArticleDO_to_ArticleTabComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
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
public class ArticleTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleTabComponentCO> {

    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;

    public ArticleTabCOAssembler(ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO) {
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
    }

    @Override
    public ArticleTabComponentCO assembleDomainToPublicModelView(ArticleDO articleDO) {

        ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToPublicView(articleDO);
        articleTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
        return addLinksWithCurrentAuthentication(articleTabComponentCO);

    }

    @Override
    public ArticleTabComponentCO assembleDomainToFullModelView(ArticleDO articleDO) {

        ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToFullView(articleDO);
        articleTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);

        return addLinksWithCurrentAuthentication(articleTabComponentCO);
    }

    @Override
    public ArticleTabComponentCO addLinksWithCurrentAuthentication(ArticleTabComponentCO entity) {
        return entity
                .add(List.of(

                        linkTo(methodOn(ArticleEditorController.class).likeArticle(entity.getObjectId(),getAuth())).withRel("LikeArticle"),
                        linkTo(methodOn(ArticleEditorController.class).dislikeArticle(entity.getObjectId(),getAuth())).withRel("DislikeArticle"),
                        linkTo(methodOn(ArticleEditorController.class).deleteArticle(entity.getObjectId(),getAuth())).withRel("DeleteArticle"),


                        linkTo(methodOn(ArticleViewerController.class).getArticleStreamComponentCO(entity.getObjectId(), getAuth())).withRel("Stream_Version"),
                        linkTo(methodOn(ArticleViewerController.class).getArticleTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version")));

    }
}

