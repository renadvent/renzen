package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Services.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

public class LoginController {

    final UserServiceImpl userService;

    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
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
    public Optional<ProfileTabComponentCO> Login(@RequestBody SitePayloads.UserNamePassword payload){
        return userService.findProfileTabComponentCOByNameAndPassword(payload.username, payload.password);
    }
}
