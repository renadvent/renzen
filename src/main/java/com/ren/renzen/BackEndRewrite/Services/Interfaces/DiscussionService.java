package com.ren.renzen.BackEndRewrite.Services.Interfaces;

import com.ren.renzen.BackEndRewrite.DomainObjects.DiscussionDO;
import org.bson.types.ObjectId;

public interface DiscussionService {

    DiscussionDO save(DiscussionDO discussionDO);

    DiscussionDO findBy_id(ObjectId id);
}
