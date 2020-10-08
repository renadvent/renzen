package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ProfileInfoComponentCO;
import com.ren.renzen.Controllers.UserViewerController;
import com.ren.renzen.Converters.ProfileDO_to_ProfileStreamComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ProfileDO, ProfileInfoComponentCO> {

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ProfileStreamCOAssembler(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ProfileInfoComponentCO assembleDomainToPublicModelView(ProfileDO profileDO) {
        ProfileInfoComponentCO profileInfoComponentCO = profileDO_to_profileStreamComponentCO.convertDomainToPublicView(profileDO);

        profileInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
        var authentication = SecurityContextHolder.getContext().getAuthentication();

//        return profileInfoComponentCO;

        return profileInfoComponentCO.add(List.of(
//                linkTo(methodOn(UserEditorController.class).getAllProfiles()).withRel("other"),
//                linkTo(methodOn(ArticleEditorController.class).getAllArticles()).withRel("other"),
//                //linkTo(methodOn(CommunityController.class).getAllCommunities()).withRel("other"),
                linkTo(methodOn(UserViewerController.class).getProfileStreamComponentCO(profileInfoComponentCO.getObjectId(),authentication)).withRel("Stream_Version"),
                linkTo(methodOn(UserViewerController.class).getProfileTabComponentCO(profileInfoComponentCO.getObjectId(),authentication)).withRel("Tab_Version"))
        );
    }

    @Override
    public ProfileInfoComponentCO assembleDomainToFullModelView(ProfileDO profileDO) {
        ProfileInfoComponentCO profileInfoComponentCO = profileDO_to_profileStreamComponentCO.convertDomainToFullView(profileDO);

        profileInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);
        var authentication = SecurityContextHolder.getContext().getAuthentication();


//        return profileInfoComponentCO;

        return profileInfoComponentCO.add(List.of(
//                linkTo(methodOn(UserEditorController.class).getAllProfiles()).withRel("other"),
//                linkTo(methodOn(ArticleEditorController.class).getAllArticles()).withRel("other"),
//                //linkTo(methodOn(CommunityController.class).getAllCommunities()).withRel("other"),
                linkTo(methodOn(UserViewerController.class).getProfileStreamComponentCO(profileInfoComponentCO.getObjectId(),authentication)).withRel("Stream_Version"),
                linkTo(methodOn(UserViewerController.class).getProfileTabComponentCO(profileInfoComponentCO.getObjectId(),authentication)).withRel("Tab_Version"))
        );
    }
}
