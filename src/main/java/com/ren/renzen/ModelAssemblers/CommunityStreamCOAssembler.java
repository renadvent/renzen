package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityInfoComponentCO;
import com.ren.renzen.Controllers.CommunityEditorController;
import com.ren.renzen.Controllers.CommunityViewerController;
import com.ren.renzen.Converters.CommunityDO_to_CommunityStreamComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import com.ren.renzen.Services.CustomUserDetailService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        return addLinksWithCurrentAuthentication(communityInfoComponentCO);

    }

    @Override
    public CommunityInfoComponentCO assembleDomainToFullModelView(CommunityDO entity) {
        CommunityInfoComponentCO communityInfoComponentCO = communityDO_to_communityStreamComponentCO.convertDomainToFullView(entity);

        communityInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);
        return addLinksWithCurrentAuthentication(communityInfoComponentCO);
    }

    @Override
    public CommunityInfoComponentCO addLinksWithCurrentAuthentication(CommunityInfoComponentCO entity) {
        return entity
                .add(List.of(
                        linkTo(methodOn(CommunityViewerController.class).getCommunityStreamComponentCO(entity.getObjectId(),getAuth())).withRel("Stream_Version"),
                        linkTo(methodOn(CommunityViewerController.class).getCommunityTabComponentCO(entity.getObjectId(),getAuth())).withRel("Tab_Version")));

    }
}




