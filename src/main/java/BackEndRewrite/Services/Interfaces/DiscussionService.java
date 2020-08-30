package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.Repositories.DiscussionRepository;

public interface DiscussionService {

    DiscussionDO save(DiscussionDO discussionDO);

    DiscussionDO findById(String id);
}
