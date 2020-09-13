package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ProfileTabComponentCO;
import com.ren.renzen.Controllers.SiteController;
import com.ren.renzen.Converters.ProfileDO_to_ProfileTabComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileTabCOAssembler extends RepresentationModelAssemblerSupport<ProfileDO, ProfileTabComponentCO> {

    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;

    public ProfileTabCOAssembler(ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO) {
        super(SiteController.class, ProfileTabComponentCO.class);
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
    }

    @Override
    public ProfileTabComponentCO toModel(ProfileDO profileDO) {

        ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convert(profileDO);

        return profileTabComponentCO.add(List.of(
                linkTo(methodOn(SiteController.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(SiteController.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(SiteController.class).getAllCommunities()).withRel("All_Communities"),
                linkTo(methodOn(SiteController.class).getProfileStreamComponentCO(profileTabComponentCO.getObjectId())).withRel("Stream_Version"),
                linkTo(methodOn(SiteController.class).getProfileTabComponentCO(profileTabComponentCO.getObjectId())).withRel("Tab_Version")));

    }

}