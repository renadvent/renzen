package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    ProfileDO save(ProfileDO profileDO);

    Set<ProfileDO> getProfileDOList();

    ProfileDO findProfileDOById(String id);
    ProfileDO findProfileDOByName(String profileName);

    boolean checkIfUsernameTaken(String name);
    ProfileDO findProfileDOByNameAndPassword(String name,String password);

    ProfileDO saveAndReturnProfileDO(ProfileDO profileDO);


}
