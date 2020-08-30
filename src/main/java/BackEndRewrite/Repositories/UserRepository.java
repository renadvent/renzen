package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ProfileDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ProfileDO,String> {
    Optional<ProfileDO> findByUsername(String username);
}
