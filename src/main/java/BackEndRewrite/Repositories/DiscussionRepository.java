package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.DiscussionDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscussionRepository extends CrudRepository<DiscussionDO,String> {
}
