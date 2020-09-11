package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityStreamComponentCO;
import com.ren.renzen.Controllers.SiteController;
import com.ren.renzen.Converters.CommunityDO_to_CommunityStreamComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommunityStreamCOAssembler extends RepresentationModelAssemblerSupport<CommunityDO, CommunityStreamComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    public CommunityStreamCOAssembler(CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO) {
        super(SiteController.class, CommunityStreamComponentCO.class);
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
    }

    @Override
    public CommunityStreamComponentCO toModel(CommunityDO entity) {

        CommunityStreamComponentCO communityStreamComponentCO = communityDO_to_communityStreamComponentCO.convert(entity);
        return communityStreamComponentCO
                .add(List.of(
                        linkTo(methodOn(SiteController.class).getCommunityStreamComponentCO(communityStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                        linkTo(methodOn(SiteController.class).getCommunityTabComponentCO(communityStreamComponentCO.getObjectId())).withRel("Tab_Version")));
    }
}




