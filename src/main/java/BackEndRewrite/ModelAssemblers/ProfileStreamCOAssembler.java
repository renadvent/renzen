package BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Controllers.StreamControllers;
import BackEndRewrite.Controllers.TabControllers;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProfileStreamCOAssembler implements RepresentationModelAssembler<ProfileStreamComponentCO, EntityModel<ProfileStreamComponentCO>> {

    @Override
    public EntityModel<ProfileStreamComponentCO> toModel(ProfileStreamComponentCO profileStreamComponentCO) {

        return EntityModel.of(profileStreamComponentCO);

//                return EntityModel.of(profileStreamComponentCO,
//                linkTo(methodOn(StreamControllers.class).getProfileStreamComponentCO(profileStreamComponentCO.get_id())).withRel("profileStreamComponentCO"),
//                linkTo(methodOn(TabControllers.class).getProfileTabComponentCO(profileStreamComponentCO.getId())).withRel("profileTabComponentCO"));

    }

    @Override
    public CollectionModel<EntityModel<ProfileStreamComponentCO>> toCollectionModel(Iterable<? extends ProfileStreamComponentCO> entities) {
        return null;
    }
}
