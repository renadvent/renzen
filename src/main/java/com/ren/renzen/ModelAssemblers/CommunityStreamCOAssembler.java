package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityInfoComponentCO;
import com.ren.renzen.Converters.CommunityDO_to_CommunityStreamComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CommunityStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<CommunityDO, CommunityInfoComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    public CommunityStreamCOAssembler(CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO) {
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
    }

    @Override
    public CommunityInfoComponentCO assembleDomainToPublicModelView(CommunityDO entity) {
        CommunityInfoComponentCO communityInfoComponentCO = communityDO_to_communityStreamComponentCO.convertDomainToPublicView(entity);

        communityInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

        return communityInfoComponentCO;

        //        return communityInfoComponentCO
//                .add(List.of(
//                        linkTo(methodOn(CommunityEditorController.class).getCommunityStreamComponentCO(communityInfoComponentCO.getObjectId())).withRel("Stream_Version"),
//                        linkTo(methodOn(CommunityEditorController.class).getCommunityTabComponentCO(communityInfoComponentCO.getObjectId())).withRel("Tab_Version")));

    }

    @Override
    public CommunityInfoComponentCO assembleDomainToFullModelView(CommunityDO entity) {
        CommunityInfoComponentCO communityInfoComponentCO = communityDO_to_communityStreamComponentCO.convertDomainToFullView(entity);

        communityInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);

        return communityInfoComponentCO;


//        return communityInfoComponentCO
//                .add(List.of(
//                        linkTo(methodOn(CommunityEditorController.class).getCommunityStreamComponentCO(communityInfoComponentCO.getObjectId())).withRel("Stream_Version"),
//                        linkTo(methodOn(CommunityEditorController.class).getCommunityTabComponentCO(communityInfoComponentCO.getObjectId())).withRel("Tab_Version")));
//
//
    }
}




