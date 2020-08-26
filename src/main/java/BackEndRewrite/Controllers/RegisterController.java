package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Component
@CrossOrigin("*")
public class RegisterController {

    final UserRepository user_repository;
    final ProfileDO_to_ProfileTabComponentCO profileTabConverter;

    public RegisterController(UserRepository user_repository, ProfileDO_to_ProfileTabComponentCO profileTabConverter) {
        this.user_repository = user_repository;
        this.profileTabConverter = profileTabConverter;
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
        if (user_repository.findByUsername(payload.username)!=null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "username is taken");
        }
        ProfileDO user = new ProfileDO(payload.username,payload.password);
        user_repository.save(user);
        return profileTabConverter.convert(user);
    }
}
