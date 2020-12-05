package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.Controllers.CommunityController;
import com.ren.renzen.Converters.CommunityConverter;
import com.ren.renzen.ResourceObjects.CommandObjects.CommunityDTOs;
import com.ren.renzen.ResourceObjects.DomainObjects.CommunityDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommunityStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<CommunityDO, CommunityDTOs.CommunityInfoComponentCO> {

    final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    public CommunityStreamCOAssembler(CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO) {
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
    }

    @Override
    public CommunityDTOs.CommunityInfoComponentCO assembleDomainToPublicModelView(CommunityDO entity) {
        CommunityDTOs.CommunityInfoComponentCO communityInfoComponentCO = communityDO_to_communityStreamComponentCO.convertDomainToPublicView(entity);

        communityInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
        return addLinksWithCurrentAuthentication(communityInfoComponentCO);

    }

    @Override
    public CommunityDTOs.CommunityInfoComponentCO assembleDomainToFullModelView(CommunityDO entity) {
        CommunityDTOs.CommunityInfoComponentCO communityInfoComponentCO = communityDO_to_communityStreamComponentCO.convertDomainToFullView(entity);

        communityInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);
        return addLinksWithCurrentAuthentication(communityInfoComponentCO);
    }

    @Override
    public CommunityDTOs.CommunityInfoComponentCO addLinksWithCurrentAuthentication(CommunityDTOs.CommunityInfoComponentCO entity) {
        return entity
                .add(List.of(
                        linkTo(methodOn(CommunityController.CommunityViewerController.class).getCommunityStreamComponentCO(entity.getObjectId(), getAuth())).withRel("Stream_Version"),
                        linkTo(methodOn(CommunityController.CommunityViewerController.class).getCommunityTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version")));

    }
}




