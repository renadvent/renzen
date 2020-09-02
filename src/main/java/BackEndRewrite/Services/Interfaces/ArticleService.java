package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ArticleService {

    ArticleDO save(ArticleDO articleDO);

    Iterable<ArticleDO> getArticleDOList();

    Iterable<ArticleDO> findArticleDOsByCommunityID(ObjectId objectId);

    ArticleDO findBy_id(ObjectId Id);
    //---------------------

    void delete(ArticleDO articleDO);

    Iterable<ArticleDO> findAll();

    List<ArticleDO> findAllByCommunityIDAndTopic(ObjectId communityID, String topic);

}
