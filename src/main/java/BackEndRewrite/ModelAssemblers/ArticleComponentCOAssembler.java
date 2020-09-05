package BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.Controllers.IndexController;
import BackEndRewrite.Converters.ArticleDO_to_ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Test Assembler for Rest
 * adds a link to the response
 *
 * use in future
 *  /profile
 *      links:
 *             /ProfileStreamCO
 *             /ProfileTabCO
 *
 *             /ListOfArticles
 *             etc....
 *
 *so use a single profile id, and get a list of possible responses.
 * in this case, possible responses are different CO's
 *
 */
@Component
public class ArticleComponentCOAssembler extends RepresentationModelAssemblerSupport<ArticleDO, ArticleComponentCO> {

    final ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO;

    public ArticleComponentCOAssembler(ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO) {
        super(IndexController.class, ArticleComponentCO.class);
        this.articleDO_to_articleComponentCO = articleDO_to_articleComponentCO;
    }

    @Override
    public ArticleComponentCO toModel(ArticleDO articleDO) {

        return articleDO_to_articleComponentCO.convert(articleDO).add(List.of(
                linkTo(methodOn(IndexController.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllCommunities()).withSelfRel()
        ));

    }

}
