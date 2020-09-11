package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.DiscussionDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends PagingAndSortingRepository<DiscussionDO, ObjectId> {
    DiscussionDO findBy_id(ObjectId objectId);
}
