package com.ren.renzen.BackEndRewrite.Repositories;

import com.ren.renzen.BackEndRewrite.DomainObjects.DiscussionDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends PagingAndSortingRepository<DiscussionDO, ObjectId> {
    DiscussionDO findBy_id(ObjectId objectId);
}
