package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends MongoRepository<CommunityDO, ObjectId> {
    Optional<CommunityDO> findByName(String name);
    ObjectId findBy_id(ObjectId objectId);
    List<CommunityDO> findByCreatorID(ObjectId objectId);
    List<CommunityDO> findBy_idIn(List<ObjectId> objectIdList);
}
