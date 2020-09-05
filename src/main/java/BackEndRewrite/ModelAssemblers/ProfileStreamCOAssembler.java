package BackEndRewrite.ModelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.Controllers.StreamControllers;
import BackEndRewrite.Converters.ProfileDO_to_ProfileStreamComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileStreamCOAssembler extends RepresentationModelAssemblerSupport<ProfileDO, ProfileStreamComponentCO> {

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;

    public ProfileStreamCOAssembler(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO) {
        super(StreamControllers.class, ProfileStreamComponentCO.class);
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
    }

    @Override
    public ProfileStreamComponentCO toModel(ProfileDO profileDO) {

        //ProfileStreamComponentCO profileStreamComponentCO = profileDO_to_profileStreamComponentCO.convert(instantiateModel(profileDO));
       ProfileStreamComponentCO profileStreamComponentCO = profileDO_to_profileStreamComponentCO.convert(profileDO);

        return profileStreamComponentCO.add(List.of(
                linkTo(methodOn(StreamControllers.class).getAllProfiles()).withRel("other"),
                linkTo(methodOn(StreamControllers.class).getAllArticles()).withRel("other"),
                linkTo(methodOn(StreamControllers.class).getAllCommunities()).withRel("other"),
                linkTo(methodOn(StreamControllers.class).getProfileStreamComponentCO(profileStreamComponentCO.getObjectId())).withRel("Stream_Version"))
                );
    }


}
