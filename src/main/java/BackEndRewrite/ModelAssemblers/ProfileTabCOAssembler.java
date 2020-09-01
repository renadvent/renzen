package BackEndRewrite.ModelAssemblers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProfileTabCOAssembler implements RepresentationModelAssembler<ProfileTabComponentCO, EntityModel<ProfileTabComponentCO>> {
    @Override
    public EntityModel<ProfileTabComponentCO> toModel(ProfileTabComponentCO entity) {

        return EntityModel.of(entity);
    }

    @Override
    public CollectionModel<EntityModel<ProfileTabComponentCO>> toCollectionModel(Iterable<? extends ProfileTabComponentCO> entities) {
        return null;
    }
}