package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.DomainObjects.ArticleDO;

public interface ArticleService {

    ArticleDO save(ArticleDO articleDO);

    Iterable<ArticleDO> getArticleDOList();

    Iterable<ArticleDO> findArticleDOsByCommunityID(String communityId);

    ArticleDO findArticleDOByID(String Id);
    //---------------------

    void delete(ArticleDO articleDO);




}
