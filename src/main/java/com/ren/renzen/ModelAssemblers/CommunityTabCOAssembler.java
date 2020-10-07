package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityTabComponentCO;
import com.ren.renzen.Converters.CommunityDO_to_CommunityTabComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

@Component
public class CommunityTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<CommunityDO, CommunityTabComponentCO> {

    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;

    public CommunityTabCOAssembler(CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO) {
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO assembleDomainToPublicModelView(CommunityDO entity) {
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convertDomainToPublicView(entity);

        return communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO assembleDomainToFullModelView(CommunityDO entity) {
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convertDomainToFullView(entity);

        return communityTabComponentCO;
    }
}
