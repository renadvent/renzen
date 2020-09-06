package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    ProfileDO save(ProfileDO profileDO);

    Iterable<ProfileDO> getProfileDOList();

    Iterable<ProfileDO> findAll();

    ProfileDO findBy_id(ObjectId id);

    List<ProfileDO> findAllBy_Id(List<ObjectId> objectIdList);

    ProfileDO findProfileDOByName(String profileName);

    boolean checkIfUsernameTaken(String name);
    ProfileDO findProfileDOByNameAndPassword(String name,String password);

    ProfileDO saveAndReturnProfileDO(ProfileDO profileDO);


}
