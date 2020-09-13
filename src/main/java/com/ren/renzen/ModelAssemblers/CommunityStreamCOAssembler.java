package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.CommunityStreamComponentCO;
import com.ren.renzen.Controllers.CommunityController;
import com.ren.renzen.Converters.CommunityDO_to_CommunityStreamComponentCO;
import com.ren.renzen.DomainObjects.CommunityDO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommunityStreamCOAssembler implements RepresentationModelAssembler<CommunityDO, CommunityStreamComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    public CommunityStreamCOAssembler(CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO) {
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
    }

    @Override
    public CommunityStreamComponentCO toModel(CommunityDO entity) {

        CommunityStreamComponentCO communityStreamComponentCO = communityDO_to_communityStreamComponentCO.convert(entity);
        return communityStreamComponentCO
                .add(List.of(
                        linkTo(methodOn(CommunityController.class).getCommunityStreamComponentCO(communityStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                        linkTo(methodOn(CommunityController.class).getCommunityTabComponentCO(communityStreamComponentCO.getObjectId())).withRel("Tab_Version")));
    }
}




