package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<ProfileDO> save(ProfileDO profileDO);

    Set<ProfileDO> getProfileDOList();

    Optional<ProfileDO> findProfileDOById(String id);
    Optional <ProfileDO> findProfileDOByName(String profileName);

    Optional<ProfileStreamComponentCO> findProfileStreamComponentCOById(String id);
    Optional<ProfileTabComponentCO> findProfileTabComponentCOById(String id);

    boolean checkIfUsernameTaken(String name);
    Optional<ProfileTabComponentCO> findProfileTabComponentCOByNameAndPassword(String name,String password);

    Optional<ProfileTabComponentCO> saveAndReturnProfileTabComponentCO(ProfileDO profileDO);


}
