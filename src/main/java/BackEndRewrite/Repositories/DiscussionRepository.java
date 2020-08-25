package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.DiscussionDO;
import org.springframework.data.repository.CrudRepository;

public interface DiscussionRepository extends CrudRepository<DiscussionDO,String> {
}
