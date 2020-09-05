package BackEndRewrite.ModelAssemblers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.Controllers.IndexController;
import BackEndRewrite.Converters.CommunityDO_to_CommunityTabComponentCO;
import BackEndRewrite.DomainObjects.CommunityDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CommunityTabCOAssembler extends RepresentationModelAssemblerSupport<CommunityDO, CommunityTabComponentCO> {

    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;

    public CommunityTabCOAssembler(CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO) {
        super(IndexController.class, CommunityTabComponentCO.class);
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO toModel(CommunityDO entity) {

        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(entity);

        return communityTabComponentCO;
    }
}
