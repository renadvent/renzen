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

    ProfileStreamComponentCO findProfileStreamComponentCOById(String id);
    ProfileTabComponentCO findProfileTabComponentCOById(String id);

    boolean checkIfUsernameTaken(String name);
    ProfileTabComponentCO findProfileTabComponentCOByNameAndPassword(String name,String password);

    ProfileTabComponentCO saveAndReturnProfileTabComponentCO(ProfileDO profileDO);


}
