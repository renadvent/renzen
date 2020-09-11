package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<ArticleDO, ObjectId> {
    Optional<ArticleDO> findBy_id(ObjectId id);
    Iterable<ArticleDO> findArticleDOSByCommunityID(ObjectId objectId);
    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);
    List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String Topic);
}
