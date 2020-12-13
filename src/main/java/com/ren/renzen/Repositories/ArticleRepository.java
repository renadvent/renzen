package com.ren.renzen.Repositories;

import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ArticleRepository extends MongoRepository<ArticleDO, ObjectId> {
    Optional<ArticleDO> findBy_id(ObjectId id);


//    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);
    List<ArticleDO> findBy_idIn(Collection<ObjectId> set);


    List<ArticleDO> findAllByCreatorIDAndWorkName(ObjectId creatorId, String workName);

    List<ArticleDO> findByIsDraft(boolean published, Pageable pageable);


//    List<ArticleDO> findAllByCreatorNameAndWorkName(ObjectId creatorId, String workName);
}
