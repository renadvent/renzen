package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends MongoRepository<ArticleDO, ObjectId> {
    Optional<ArticleDO> findBy_id(ObjectId id);

    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);

    List<ArticleDO> findAllByCreatorIDAndWorkName(ObjectId creatorId, String workName);


//    List<ArticleDO> findAllByCreatorNameAndWorkName(ObjectId creatorId, String workName);
}
