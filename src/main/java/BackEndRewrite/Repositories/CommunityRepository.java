package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.CommunityDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends CrudRepository<CommunityDO,String> {
    Optional<CommunityDO> findByName(String name);
}
