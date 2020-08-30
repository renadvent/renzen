package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.CommunityDO;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommunityRepository extends CrudRepository<CommunityDO,String> {
    Optional<CommunityDO> findByName(String name);
}
