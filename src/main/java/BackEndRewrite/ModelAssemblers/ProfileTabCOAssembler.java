package BackEndRewrite.ModelAssemblers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Controllers.CreateArticleController;
import BackEndRewrite.Controllers.IndexController;
import BackEndRewrite.Controllers.StreamControllers;
import BackEndRewrite.Controllers.TabControllers;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileTabCOAssembler implements RepresentationModelAssembler<ProfileTabComponentCO, EntityModel<ProfileTabComponentCO>> {
    @Override
    public EntityModel<ProfileTabComponentCO> toModel(ProfileTabComponentCO entity) {

        return EntityModel.of(entity,
                linkTo(methodOn(StreamControllers.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(StreamControllers.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(StreamControllers.class).getAllCommunities()).withSelfRel());

                //linkTo(methodOn(TabControllers.class).getProfileTabComponentCO(entity.getId())).withRel("profileTabComponentCO"));
//);
    }

    @Override
    public CollectionModel<EntityModel<ProfileTabComponentCO>> toCollectionModel(Iterable<? extends ProfileTabComponentCO> entities) {
        return null;
    }
}
