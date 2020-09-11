package com.ren.renzen.BackEndRewrite.ModelAssemblers;

import com.ren.renzen.BackEndRewrite.CommandObjects.CommunityStreamComponentCO;
import com.ren.renzen.BackEndRewrite.Controllers.IndexController;
import com.ren.renzen.BackEndRewrite.Converters.CommunityDO_to_CommunityStreamComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CommunityStreamCOAssembler extends RepresentationModelAssemblerSupport<CommunityDO, CommunityStreamComponentCO> {

    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    public CommunityStreamCOAssembler(CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO) {
        super(IndexController.class, CommunityStreamComponentCO.class);
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
    }

    @Override
    public CommunityStreamComponentCO toModel(CommunityDO entity) {

        CommunityStreamComponentCO communityStreamComponentCO = communityDO_to_communityStreamComponentCO.convert(entity);
        return communityStreamComponentCO
                .add(List.of(
                        linkTo(methodOn(IndexController.class).getCommunityStreamComponentCO(communityStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                        linkTo(methodOn(IndexController.class).getCommunityTabComponentCO(communityStreamComponentCO.getObjectId())).withRel("Tab_Version")));
    }
}




