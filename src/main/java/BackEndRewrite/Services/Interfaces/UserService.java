package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.UserRepository;

import java.util.Set;

public interface UserService {

    Set<ProfileDO> getProfileDOList();

    ProfileDO findProfileDOById(String id);
    ProfileDO findProfileDOByName(String profileName);

    ProfileStreamComponentCO findProfileStreamComponentCOById(String id);
    ProfileTabComponentCO findProfileTabComponentCOById(String id);

    boolean checkIfUsernameTaken(String name);
    ProfileTabComponentCO findProfileTabComponentCOByNameAndPassword(String name,String password);

    ProfileTabComponentCO saveAndReturnProfileTabComponentCO(ProfileDO profileDO);


}
