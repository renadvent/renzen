package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.Repositories.DiscussionRepository;
import org.bson.types.ObjectId;

public interface DiscussionService {

    DiscussionDO save(DiscussionDO discussionDO);

    DiscussionDO findBy_id(ObjectId id);
}
