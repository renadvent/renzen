package BackEndRewrite.Services;

import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.Repositories.DiscussionRepository;
import BackEndRewrite.Services.Interfaces.DiscussionService;
import org.bson.types.ObjectId;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    final DiscussionRepository discussionRepository;

    public DiscussionServiceImpl(DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
    }

    @Override
    public DiscussionDO save(DiscussionDO discussionDO) {

        return discussionRepository.save(discussionDO);
    }

    @Override
    public DiscussionDO findById(ObjectId id) {

        Optional<DiscussionDO> discussionDO = discussionRepository.findById(id);

        if (discussionDO.isPresent()){
            return discussionDO.get();
        }else{
            throw new ResourceNotFoundException("discussion id not found");
        }

    }
}
