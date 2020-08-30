package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Accesses User Repository
 */

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ProfileDO save(ProfileDO profileDO) {
        return null;
    }

    @Override
    public Set<ProfileDO> getProfileDOList() {
        return null;
    }

    @Override
    public ProfileDO findProfileDOById(String id) {
        return null;
    }

    @Override
    public ProfileDO findProfileDOByName(String profileName) {
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

    @Override
    public ProfileDO findProfileDOByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public ProfileDO saveAndReturnProfileDO(ProfileDO profileDO) {
        return null;
    }

    /**
     * used by login controller
     * @param name
     * @param password
     * @return
     */

}
