package BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import BackEndRewrite.Controllers.CreateArticleController;
import BackEndRewrite.DomainObjects.ArticleDO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

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
 *so use a single profile id, and get a list of possible responses.
 * in this case, possible responses are different CO's
 *
 */
public class TestAssembler implements RepresentationModelAssembler<ArticleDO, EntityModel<ArticleDO>> {
    @Override
    public EntityModel<ArticleDO> toModel(ArticleDO articleDO) {
        return EntityModel.of(articleDO,
                linkTo(methodOn(CreateArticleController.class).doNothing()).withSelfRel());
    }

    @Override
    public CollectionModel<EntityModel<ArticleDO>> toCollectionModel(Iterable<? extends ArticleDO> entities) {
        return null;
    }
}
