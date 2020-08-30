package BackEndRewrite.Services.Interfaces;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface ArticleService {

    ArticleDO save(ArticleDO articleDO);

    ArticleDO getArticleDOList();

    Set<ArticleDO> findArticleDOsByCommunityID(String communityId);

    ArticleDO findArticleDOByID(String Id);
    //---------------------

    void save();

    void delete();




}
