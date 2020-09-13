package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.DiscussionDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscussionRepository extends MongoRepository<DiscussionDO, ObjectId> {
    Optional<DiscussionDO> findBy_id(ObjectId objectId);
}
