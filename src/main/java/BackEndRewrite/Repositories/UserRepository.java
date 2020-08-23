package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ProfileDO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<ProfileDO,String> {
    ProfileDO findByUsername(String username);
}
