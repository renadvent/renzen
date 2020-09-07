package BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import BackEndRewrite.CommandObjects.TabComponentCOs.ArticleTabComponentCO;
import BackEndRewrite.Controllers.IndexController;
import BackEndRewrite.Converters.ArticleDO_to_ArticleTabComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
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
public class ArticleTabCOAssembler extends RepresentationModelAssemblerSupport<ArticleDO, ArticleTabComponentCO> {

    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;

    public ArticleTabCOAssembler(ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO) {
        super(IndexController.class, ArticleTabComponentCO.class);
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
    }

    @Override
    public ArticleTabComponentCO toModel(ArticleDO articleDO) {



        return articleDO_to_articleTabComponentCO.convert(articleDO).add(List.of(

                linkTo(methodOn(IndexController.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllCommunities()).withSelfRel()
        ));

    }

}

