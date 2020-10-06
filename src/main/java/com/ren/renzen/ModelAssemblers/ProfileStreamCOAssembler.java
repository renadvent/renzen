package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ProfileStreamComponentCO;
import com.ren.renzen.Controllers.ArticleController;
import com.ren.renzen.Controllers.CommunityController;
import com.ren.renzen.Controllers.UserController;
import com.ren.renzen.Converters.ProfileDO_to_ProfileStreamComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileStreamCOAssembler implements RepresentationModelAssembler<ProfileDO, ProfileStreamComponentCO> {

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ProfileStreamCOAssembler(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ProfileStreamComponentCO toModel(ProfileDO profileDO) {

        ProfileStreamComponentCO profileStreamComponentCO = profileDO_to_profileStreamComponentCO.convert(profileDO);

        return profileStreamComponentCO.add(List.of(
                linkTo(methodOn(UserController.class).getAllProfiles()).withRel("other"),
                linkTo(methodOn(ArticleController.class).getAllArticles()).withRel("other"),
                //linkTo(methodOn(CommunityController.class).getAllCommunities()).withRel("other"),
                linkTo(methodOn(UserController.class).getProfileStreamComponentCO(profileStreamComponentCO.getObjectId())).withRel("Stream_Version"),
                linkTo(methodOn(UserController.class).getProfileTabComponentCO(profileStreamComponentCO.getObjectId())).withRel("Tab_Version"))
        );
    }


}
