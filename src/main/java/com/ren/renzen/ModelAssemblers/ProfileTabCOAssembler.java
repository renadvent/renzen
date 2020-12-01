package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.CommandObjects.ProfileDTOs;
import com.ren.renzen.Controllers.UserViewerController;
import com.ren.renzen.Converters.ProfileDO_to_ProfileTabComponentCO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileTabCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ProfileDO, ProfileDTOs.ProfileTabComponentCO> {

    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;

    public ProfileTabCOAssembler(ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO) {
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
    }

    @Override
    public ProfileDTOs.ProfileTabComponentCO assembleDomainToPublicModelView(ProfileDO profileDO) {
        ProfileDTOs.ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convertDomainToPublicView(profileDO);

        profileTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
        return addLinksWithCurrentAuthentication(profileTabComponentCO);

    }

    @Override
    public ProfileDTOs.ProfileTabComponentCO assembleDomainToFullModelView(ProfileDO profileDO) {
        ProfileDTOs.ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convertDomainToFullView(profileDO);

        profileTabComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);
        return addLinksWithCurrentAuthentication(profileTabComponentCO);

    }

    @Override
    public ProfileDTOs.ProfileTabComponentCO addLinksWithCurrentAuthentication(ProfileDTOs.ProfileTabComponentCO entity) {
        return entity.add(List.of(
                linkTo(methodOn(UserViewerController.class).getProfileStreamComponentCO(entity.getObjectId(), getAuth())).withRel("Stream_Version"),
                linkTo(methodOn(UserViewerController.class).getProfileTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version"))
        );
    }
}
