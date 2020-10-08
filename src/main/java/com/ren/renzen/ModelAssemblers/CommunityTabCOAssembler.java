package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityTabComponentCO;
import com.ren.renzen.Converters.CommunityDO_to_CommunityTabComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;

@Component
public class CommunityTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<CommunityDO, CommunityTabComponentCO> {

    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;

    public CommunityTabCOAssembler(CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO) {
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO assembleDomainToPublicModelView(CommunityDO entity) {
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convertDomainToPublicView(entity);

        communityTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

        return communityTabComponentCO;
    }

    @Override
    public CommunityTabComponentCO assembleDomainToFullModelView(CommunityDO entity) {
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convertDomainToFullView(entity);

        communityTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);

        return communityTabComponentCO;
    }
}
