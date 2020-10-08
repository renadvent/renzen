package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.Controllers.ArticleViewerController;
import com.ren.renzen.Converters.ArticleDO_to_ArticleTabComponentCO;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class ArticleTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ArticleDO, ArticleTabComponentCO> {

    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;

    public ArticleTabCOAssembler(ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO) {
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
    }

    @Override
    public ArticleTabComponentCO assembleDomainToPublicModelView(ArticleDO articleDO) {

            ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToPublicView(articleDO);
        var authentication = SecurityContextHolder.getContext().getAuthentication();


//            return articleTabComponentCO;

            return articleTabComponentCO

                   .add(List.of(
                            linkTo(methodOn(ArticleViewerController.class).getArticleStreamComponentCO(articleTabComponentCO.getObjectId(),authentication)).withRel("Stream_Version"),
                            linkTo(methodOn(ArticleViewerController.class).getArticleTabComponentCO(articleTabComponentCO.getObjectId(),authentication)).withRel("Tab_Version")));

    }

    @Override
    public ArticleTabComponentCO assembleDomainToFullModelView(ArticleDO articleDO) {

            ArticleTabComponentCO articleTabComponentCO = articleDO_to_articleTabComponentCO.convertDomainToFullView(articleDO);
        var authentication = SecurityContextHolder.getContext().getAuthentication();


//            return articleTabComponentCO;

            return articleTabComponentCO

                   .add(List.of(
                linkTo(methodOn(ArticleViewerController.class).getArticleStreamComponentCO(articleTabComponentCO.getObjectId(),authentication)).withRel("Stream_Version"),
                linkTo(methodOn(ArticleViewerController.class).getArticleTabComponentCO(articleTabComponentCO.getObjectId(),authentication)).withRel("Tab_Version")));

    }
}

