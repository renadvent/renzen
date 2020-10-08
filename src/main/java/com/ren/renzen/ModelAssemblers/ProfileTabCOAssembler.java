package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ProfileTabComponentCO;
import com.ren.renzen.Converters.ProfileDO_to_ProfileTabComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ProfileTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ProfileDO, ProfileTabComponentCO> {

    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;

    public ProfileTabCOAssembler(ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO) {
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
    }

    @Override
    public ProfileTabComponentCO assembleDomainToPublicModelView(ProfileDO profileDO) {
        ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convertDomainToPublicView(profileDO);

        profileTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);

        return profileTabComponentCO;

//        return profileTabComponentCO.add(List.of(
//                linkTo(methodOn(UserEditorController.class).getAllProfiles()).withSelfRel(),
//                linkTo(methodOn(ArticleEditorController.class).getAllArticles()).withSelfRel(),
//                //linkTo(methodOn(CommunityController.class).getAllCommunities()).withRel("All_Communities"),
//                linkTo(methodOn(UserEditorController.class).getProfileStreamComponentCO(profileTabComponentCO.getObjectId())).withRel("Stream_Version"),
//                linkTo(methodOn(UserEditorController.class).getProfileTabComponentCO(profileTabComponentCO.getObjectId())).withRel("Tab_Version")));
//

    }

    @Override
    public ProfileTabComponentCO assembleDomainToFullModelView(ProfileDO profileDO) {
        ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convertDomainToFullView(profileDO);

        profileTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);

        return profileTabComponentCO;

//        return profileTabComponentCO.add(List.of(
//                linkTo(methodOn(UserEditorController.class).getAllProfiles()).withSelfRel(),
//                linkTo(methodOn(ArticleEditorController.class).getAllArticles()).withSelfRel(),
//                //linkTo(methodOn(CommunityController.class).getAllCommunities()).withRel("All_Communities"),
//                linkTo(methodOn(UserEditorController.class).getProfileStreamComponentCO(profileTabComponentCO.getObjectId())).withRel("Stream_Version"),
//                linkTo(methodOn(UserEditorController.class).getProfileTabComponentCO(profileTabComponentCO.getObjectId())).withRel("Tab_Version")));
//

    }
}
