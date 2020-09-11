package com.ren.renzen.BackEndRewrite.Repositories;

import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<ArticleDO, ObjectId> {
    Optional<ArticleDO> findBy_id(ObjectId id);
    Iterable<ArticleDO> findArticleDOSByCommunityID(ObjectId objectId);
    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);
    List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic);
}
