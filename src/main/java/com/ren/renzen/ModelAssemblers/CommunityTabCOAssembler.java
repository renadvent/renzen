package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityTabComponentCO;
import com.ren.renzen.Converters.CommunityDO_to_CommunityTabComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CommunityTabCOAssembler implements RepresentationModelAssembler<CommunityDO, CommunityTabComponentCO> {

    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;

    public CommunityTabCOAssembler(CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO) {
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO toModel(CommunityDO entity) {

        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(entity);

        return communityTabComponentCO;
    }
}
