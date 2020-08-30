package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.ModelAssemblers.ProfileTabCOAssembler;
import BackEndRewrite.Services.UserServiceImpl;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@CrossOrigin("*")
@RestController
public class RegisterController {

    final UserServiceImpl userService;
    final ProfileTabCOAssembler profileTabCOAssembler;

    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;


    public RegisterController(UserServiceImpl userService, ProfileTabCOAssembler profileTabCOAssembler, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO) {
        this.userService = userService;
        this.profileTabCOAssembler = profileTabCOAssembler;
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
    }

    /**
     * Used to register a username and password
     * Returns info to form a user tab component in react
     *
     * @param payload
     * @return
     */

    @RequestMapping(path="/register")
    @ResponseBody
    public ResponseEntity<?> Register(@RequestBody SitePayloads.UserNamePassword payload){

        if (userService.checkIfUsernameTaken(payload.username)){
            throw new RuntimeException("user name taken");
        }else{

            //create profile
            ProfileDO profileDO = new ProfileDO(payload.username,payload.password);

            //get tab component
            ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convert(profileDO);

            //assemble
            EntityModel<ProfileTabComponentCO> entityModel = profileTabCOAssembler.toModel(profileTabComponentCO);

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);

        }
    }

}
