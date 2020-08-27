package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Services.UserServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@CrossOrigin("*")
public class RegisterController {

    final UserServiceImpl userService;

    public RegisterController(UserServiceImpl userService) {
        this.userService = userService;
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
    public ProfileTabComponentCO Register(@RequestBody SitePayloads.UserNamePassword payload){
        userService.checkIfUsernameTaken(payload.username);
        ProfileDO user = new ProfileDO(payload.username,payload.password);
        return userService.saveAndReturnProfileTabComponentCO(user);
    }

}
