package BackEndRewrite.Services;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * Accesses User Repository
 */

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final ProfileDO_to_ProfileTabComponentCO profileTabConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileDO_to_ProfileTabComponentCO profileTabConverter) {
        this.userRepository = userRepository;
        this.profileTabConverter = profileTabConverter;
    }

    @Override
    public ProfileDO save(ProfileDO profileDO) {
        return null;
    }

    @Override
    public Set<ProfileDO> getProfileDOList() {
        return null;
    }


    /**
     * used by register controller
     * @param name
     * @return
     */
    @Override
    public boolean checkIfUsernameTaken(String name) {

        ProfileDO profileDO = userRepository.findByUsername(name);
        if (userRepository.findByUsername(name)!=null){
            return true;
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "username is taken");
        }

        return false;
    }

    /**
     * used by login controller
     * @param name
     * @param password
     * @return
     */
    @Override
    public ProfileTabComponentCO findProfileTabComponentCOByNameAndPassword(String name, String password) {

        ProfileDO profile = userRepository.findByUsername(name);
        if (profile!=null){
            if (password.equals(profile.getPassword())){
                return (profileTabConverter.convert(profile));
            }
        }else{
            throw new RuntimeException("Profile not found");
        }

    }

    @Override
    public ProfileTabComponentCO saveAndReturnProfileTabComponentCO(ProfileDO profileDO) {
        return(profileTabConverter.convert(userRepository.save(profileDO)));
        //return null;
    }
}
