package BackEndRewrite.Repositories;

import BackEndRewrite.DomainObjects.DiscussionDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends CrudRepository<DiscussionDO, ObjectId> {
    DiscussionDO findBy_id(ObjectId objectId);
}
