package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityTabComponentCO;
import com.ren.renzen.Controllers.SiteController;
import com.ren.renzen.Converters.CommunityDO_to_CommunityTabComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CommunityTabCOAssembler extends RepresentationModelAssemblerSupport<CommunityDO, CommunityTabComponentCO> {

    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;

    public CommunityTabCOAssembler(CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO) {
        super(SiteController.class, CommunityTabComponentCO.class);
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO toModel(CommunityDO entity) {

        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(entity);

        return communityTabComponentCO;
    }
}
