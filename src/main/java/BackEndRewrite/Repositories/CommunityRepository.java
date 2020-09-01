package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends CrudRepository<CommunityDO, ObjectId> {
    Optional<CommunityDO> findByName(String name);
}
