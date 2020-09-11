package com.ren.renzen.BackEndRewrite.Services.Interfaces;

import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

public interface ArticleService {

    ArticleDO save(ArticleDO articleDO);
    Iterable<ArticleDO> getArticleDOList();
    Iterable<ArticleDO> findArticleDOsByCommunityID(ObjectId objectId);
    ArticleDO findBy_id(ObjectId Id);
    List<ArticleDO> findBy_idIn(List<ObjectId> objectIdList);
    //---------------------
    void delete(ArticleDO articleDO);
    Iterable<ArticleDO> findAll();
    List<ArticleDO> findAllPage();
    List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String topic);
}
