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
    public Optional<ProfileDO> save(ProfileDO profileDO) {
        return Optional.empty();
    }

    @Override
    public Set<ProfileDO> getProfileDOList() {
        return null;
    }

    @Override
    public Optional<ProfileDO> findProfileDOById(String id) {
        return null;
    }

    @Override
    public Optional<ProfileDO> findProfileDOByName(String profileName) {
        return null;
    }

    @Override
    public Optional<ProfileStreamComponentCO> findProfileStreamComponentCOById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<ProfileTabComponentCO> findProfileTabComponentCOById(String id) {
        return Optional.empty();
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
    public Optional<ProfileTabComponentCO> findProfileTabComponentCOByNameAndPassword(String name, String password) {

        ProfileDO profile = userRepository.findByUsername(name);
        if (profile!=null){
            if (password.equals(profile.getPassword())){
                return (Optional.of(profileTabConverter.convert(profile)));
            }
        }
        return null;
    }

    @Override
    public Optional<ProfileTabComponentCO> saveAndReturnProfileTabComponentCO(ProfileDO profileDO) {
        return(Optional.of(profileTabConverter.convert(userRepository.save(profileDO))));
        //return null;
    }
}
