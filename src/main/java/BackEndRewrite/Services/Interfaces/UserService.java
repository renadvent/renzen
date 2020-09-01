package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;

public interface UserService {

    ProfileDO save(ProfileDO profileDO);

    Iterable<ProfileDO> getProfileDOList();

    ProfileDO findProfileDOById(ObjectId id);
    ProfileDO findProfileDOByName(String profileName);

    boolean checkIfUsernameTaken(String name);
    ProfileDO findProfileDOByNameAndPassword(String name,String password);

    ProfileDO saveAndReturnProfileDO(ProfileDO profileDO);


}
