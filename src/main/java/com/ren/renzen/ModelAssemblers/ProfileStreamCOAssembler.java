package com.ren.renzen.ModelAssemblers;

import com.ren.renzen.Converters.ProfileConverter;
import com.ren.renzen.ResourceObjects.CommandObjects.ProfileDTOs;
import com.ren.renzen.Controllers.UserViewerController;
import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.InterfaceAndAbstract.DOMAIN_VIEW_ASSEMBLER_SUPPORT;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_FULL;
import static com.ren.renzen.Converters.InterfaceAndAbstract.DOMAIN_VIEW_CONVERTER.ACCESS_TYPE_PUBLIC;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileStreamCOAssembler extends DOMAIN_VIEW_ASSEMBLER_SUPPORT<ProfileDO, ProfileDTOs.ProfileInfoComponentCO> {

    final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ProfileStreamCOAssembler(ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ProfileDTOs.ProfileInfoComponentCO assembleDomainToPublicModelView(ProfileDO profileDO) {
        ProfileDTOs.ProfileInfoComponentCO profileInfoComponentCO = profileDO_to_profileStreamComponentCO.convertDomainToPublicView(profileDO);

        profileInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_PUBLIC);
        return addLinksWithCurrentAuthentication(profileInfoComponentCO);

    }

    @Override
    public ProfileDTOs.ProfileInfoComponentCO assembleDomainToFullModelView(ProfileDO profileDO) {
        ProfileDTOs.ProfileInfoComponentCO profileInfoComponentCO = profileDO_to_profileStreamComponentCO.convertDomainToFullView(profileDO);

        profileInfoComponentCO.setACCESS_TYPE(ACCESS_TYPE_FULL);
        return addLinksWithCurrentAuthentication(profileInfoComponentCO);

    }

    @Override
    public ProfileDTOs.ProfileInfoComponentCO addLinksWithCurrentAuthentication(ProfileDTOs.ProfileInfoComponentCO entity) {
        return entity.add(List.of(

                //linkTo(methodOn(UserEditorController.class))

                linkTo(methodOn(UserViewerController.class).getProfileStreamComponentCO(entity.getObjectId(), getAuth())).withRel("Stream_Version"),
                linkTo(methodOn(UserViewerController.class).getProfileTabComponentCO(entity.getObjectId(), getAuth())).withRel("Tab_Version"))
        );
    }
}
