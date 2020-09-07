package com.ren.renzen.BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import com.ren.renzen.BackEndRewrite.Controllers.IndexController;
import com.ren.renzen.BackEndRewrite.Converters.ProfileDO_to_ProfileStreamComponentCO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileStreamCOAssembler extends RepresentationModelAssemblerSupport<ProfileDO, ProfileStreamComponentCO> {

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ProfileStreamCOAssembler(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        super(IndexController.class, ProfileStreamComponentCO.class);
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ProfileStreamComponentCO toModel(ProfileDO profileDO) {

       ProfileStreamComponentCO profileStreamComponentCO = profileDO_to_profileStreamComponentCO.convert(profileDO);

        return profileStreamComponentCO.add(List.of(
                linkTo(methodOn(IndexController.class).getAllProfiles()).withRel("other"),
                linkTo(methodOn(IndexController.class).getAllArticles()).withRel("other"),
                linkTo(methodOn(IndexController.class).getAllCommunities()).withRel("other"),
                linkTo(methodOn(IndexController.class).getProfileStreamComponentCO(profileStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                linkTo(methodOn(IndexController.class).getProfileTabComponentCO(profileStreamComponentCO.getObjectId())).withRel("Tab_Version"))
                );
    }


}
