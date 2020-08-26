package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.CommunityDO;
import org.springframework.data.repository.CrudRepository;

public interface CommunityRepository extends CrudRepository<CommunityDO,String> {
    CommunityDO findByName(String name);
}
