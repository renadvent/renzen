package com.ren.renzen.Repositories;

import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<ProfileDO, ObjectId> {
    Optional<ProfileDO> findByUsername(String username);

    Optional<ProfileDO> findByEmail(String email);


    Optional<ProfileDO> findBy_id(ObjectId _id);

    List<ProfileDO> findAllBy_id(Collection<ObjectId> objectIdList);
}
