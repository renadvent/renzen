package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends MongoRepository<ArticleDO, ObjectId> {
    Optional<ArticleDO> findBy_id(ObjectId id);
    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);
}
