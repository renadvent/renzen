package com.ren.renzen.BackEndRewrite.Repositories;

import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<ProfileDO, ObjectId> {
    Optional<ProfileDO> findByUsername(String username);
    Optional<ProfileDO> findById(ObjectId objectId);
    ProfileDO findBy_id(ObjectId _id);
    List<ProfileDO> findAllBy_id(List<ObjectId> objectIdList);
}
