package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.ArticleDO;
import org.bson.types.ObjectId;

public interface ArticleService {

    ArticleDO save(ArticleDO articleDO);

    Iterable<ArticleDO> getArticleDOList();

    Iterable<ArticleDO> findArticleDOsByCommunityID(String ObjectId);

    ArticleDO findArticleDOByID(ObjectId Id);
    //---------------------

    void delete(ArticleDO articleDO);




}
