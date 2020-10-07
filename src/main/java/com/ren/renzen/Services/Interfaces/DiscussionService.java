package com.ren.renzen.Services.Interfaces;

import org.bson.types.ObjectId;

public interface DiscussionService {
    DiscussionDO save(DiscussionDO discussionDO);

    DiscussionDO findBy_id(ObjectId id);
}
