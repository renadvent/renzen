package com.ren.renzen.BackEndRewrite.Repositories;

import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends CrudRepository<CommunityDO, ObjectId> {
    Optional<CommunityDO> findByName(String name);
    ObjectId findBy_id(ObjectId objectId);
    List<CommunityDO> findByCreatorID(ObjectId objectId);
    List<CommunityDO> findBy_idIn(List<ObjectId> objectIdList);
}
