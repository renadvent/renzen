package com.ren.renzen.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ren.renzen.CommandObjects.ProfileStreamComponentCO;
import com.ren.renzen.Controllers.SiteController;
import com.ren.renzen.Converters.ProfileDO_to_ProfileStreamComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileStreamCOAssembler extends RepresentationModelAssemblerSupport<ProfileDO, ProfileStreamComponentCO> {

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ProfileStreamCOAssembler(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        super(SiteController.class, ProfileStreamComponentCO.class);
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ProfileStreamComponentCO toModel(ProfileDO profileDO) {

       ProfileStreamComponentCO profileStreamComponentCO = profileDO_to_profileStreamComponentCO.convert(profileDO);

        return profileStreamComponentCO.add(List.of(
                linkTo(methodOn(SiteController.class).getAllProfiles()).withRel("other"),
                linkTo(methodOn(SiteController.class).getAllArticles()).withRel("other"),
                linkTo(methodOn(SiteController.class).getAllCommunities()).withRel("other"),
                linkTo(methodOn(SiteController.class).getProfileStreamComponentCO(profileStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                linkTo(methodOn(SiteController.class).getProfileTabComponentCO(profileStreamComponentCO.getObjectId())).withRel("Tab_Version"))
                );
    }


}
