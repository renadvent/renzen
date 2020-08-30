package BackEndRewrite.ModelAssemblers;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CommunityTabCOAssembler implements RepresentationModelAssembler<CommunityTabComponentCO, EntityModel<CommunityTabComponentCO>> {
    @Override
    public EntityModel<CommunityTabComponentCO> toModel(CommunityTabComponentCO entity) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<CommunityTabComponentCO>> toCollectionModel(Iterable<? extends CommunityTabComponentCO> entities) {
        return null;
    }
}
