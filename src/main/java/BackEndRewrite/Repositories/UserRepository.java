package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.ProfileDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<ProfileDO,String> {
    Optional<ProfileDO> findByUsername(String username);
}
