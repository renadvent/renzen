package com.ren.renzen.BackEndRewrite.Services;

import com.ren.renzen.BackEndRewrite.DomainObjects.DiscussionDO;
import com.ren.renzen.BackEndRewrite.Repositories.DiscussionRepository;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.DiscussionService;
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
    public DiscussionDO findBy_id(ObjectId id) {

        Optional<DiscussionDO> discussionDO = discussionRepository.findById(id);

        if (discussionDO.isPresent()){
            return discussionDO.get();
        }else{
            throw new ResourceNotFoundException("discussion id not found");
        }

    }
}
