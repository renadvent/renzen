package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class LoginController {

    final UserRepository user_repository;
    final ProfileDO_to_ProfileTabComponentCO profileTabConverter;

    public LoginController(UserRepository user_repository, ProfileDO_to_ProfileTabComponentCO profileTabConverter) {
        this.user_repository = user_repository;
        this.profileTabConverter = profileTabConverter;
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
        ProfileDO profile = user_repository.findByUsername(payload.username);
        if (profile!=null){
            if (payload.password.equals(profile.getPassword())){
                return (profileTabConverter.convert(profile));
            }
        }
        return null;
    }
}
