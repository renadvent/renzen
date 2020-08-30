package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.Services.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    final UserServiceImpl userService;
    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;

    public LoginController(UserServiceImpl userService, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO) {
        this.userService = userService;
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
    }

    /**
     * Used to login to website using username and password
     * Returns info to form a user tab component in react
     *
     * @param payload
     * @return
     */
    @RequestMapping(path="/login")
    @ResponseBody
    public ProfileTabComponentCO Login(@RequestBody SitePayloads.UserNamePassword payload){
        return profileDO_to_profileTabComponentCO
                .convert(userService.findProfileDOByNameAndPassword(payload.username, payload.password));
    }
}
