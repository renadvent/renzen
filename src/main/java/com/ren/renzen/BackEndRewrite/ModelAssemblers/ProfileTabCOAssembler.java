package com.ren.renzen.BackEndRewrite.ModelAssemblers;

import com.ren.renzen.BackEndRewrite.CommandObjects.ProfileTabComponentCO;
import com.ren.renzen.BackEndRewrite.Controllers.IndexController;
import com.ren.renzen.BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileTabCOAssembler extends RepresentationModelAssemblerSupport<ProfileDO, ProfileTabComponentCO> {

    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;

    public ProfileTabCOAssembler(ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO) {
        super(IndexController.class, ProfileTabComponentCO.class);
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
    }

    @Override
    public ProfileTabComponentCO toModel(ProfileDO profileDO) {

        ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convert(profileDO);

        return profileTabComponentCO.add(List.of(
                linkTo(methodOn(IndexController.class).getAllProfiles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllArticles()).withSelfRel(),
                linkTo(methodOn(IndexController.class).getAllCommunities()).withRel("All_Communities"),
                linkTo(methodOn(IndexController.class).getProfileStreamComponentCO(profileTabComponentCO.getObjectId())).withRel("Stream_Version"),
                linkTo(methodOn(IndexController.class).getProfileTabComponentCO(profileTabComponentCO.getObjectId())).withRel("Tab_Version")));

    }

}
