package com.ren.renzen.Services.Interfaces;

import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;

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

}
