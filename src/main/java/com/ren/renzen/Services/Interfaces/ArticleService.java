package com.ren.renzen.Services.Interfaces;

import com.ren.renzen.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    ArticleDO save(ArticleDO articleDO);

    //    List<ArticleDO> getArticleDOList();
    ArticleDO findBy_id(ObjectId Id);

    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);

    List<ArticleDO> findAll();

    List<ArticleDO> findAllPage();
//    List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String topic);

    List<ArticleDO> findAllByCreatorIDAndWorkName(ObjectId creator, String workName);

    List<ArticleDO> findAllPage(int page);

    Boolean deleteArticle(ObjectId id);

    List<ArticleDO> findByIsDraft(boolean published, int page);

}
