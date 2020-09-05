package BackEndRewrite.ModelAssemblers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Controllers.IndexController;
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
                linkTo(methodOn(IndexController.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllCommunities()).withRel("All_Communities"),
                linkTo(methodOn(IndexController.class).getProfileStreamComponentCO(entity.getObjectId())).withRel("Stream_Version"));

                //linkTo(methodOn(TabControllers.class).getProfileTabComponentCO(entity.getId())).withRel("profileTabComponentCO"));
//);
    }

    @Override
    public CollectionModel<EntityModel<ProfileTabComponentCO>> toCollectionModel(Iterable<? extends ProfileTabComponentCO> entities) {
        return null;
    }
}
