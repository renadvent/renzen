package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ProfileDO save(ProfileDO profileDO) {
        return userRepository.save(profileDO);
    }

    @Override
    public Iterable<ProfileDO> getProfileDOList() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<ProfileDO> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ProfileDO findBy_id(ObjectId id) {
        return userRepository.findBy_id(id);
    }

    @Override
    public ProfileDO findProfileDOByName(String profileName) {

        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(profileName);

        if (profileDOOptional.isEmpty()){
            throw new ResourceNotFoundException("id not found");
        }else {
            return profileDOOptional.get();
        }

    }

    @Override
    public boolean checkIfUsernameTaken(String name) {

        if (userRepository.findByUsername(name).isPresent()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ProfileDO findProfileDOByNameAndPassword(String name, String password) {


        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(name);

        if (profileDOOptional.isPresent()){
            if (profileDOOptional.get().getPassword() == password){
                return profileDOOptional.get();
            }else{
                throw new RuntimeException("username/password not found");
            }
        }else{
            throw new RuntimeException("username/password not found");
        }
    }

    @Override
    public ProfileDO saveAndReturnProfileDO(ProfileDO profileDO) {
        return userRepository.save(profileDO);
    }

}
