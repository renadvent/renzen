package BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.Controllers.IndexController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

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
public class ArticleComponentCOAssembler implements RepresentationModelAssembler<ArticleComponentCO, EntityModel<ArticleComponentCO>> {
    @Override
    public EntityModel<ArticleComponentCO> toModel(ArticleComponentCO articleComponentCO) {

//        return EntityModel.of(articleComponentCO);

        return EntityModel.of(articleComponentCO,
                linkTo(methodOn(IndexController.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllCommunities()).withSelfRel());
                //add a discussion link?
                //article metadata component link
                //linkTo(methodOn(StreamControllers.class).getArticleStreamComponentCO(articleComponentCO.getId())).withRel("ArticleStreamComponentCO"));


    }

    @Override
    public CollectionModel<EntityModel<ArticleComponentCO>> toCollectionModel(Iterable<? extends ArticleComponentCO> entities) {
        return null;
    }
}
