package BackEndRewrite.CommandObjects;

import BackEndRewrite.DomainObjectBases.BaseEntity;
import BackEndRewrite.DomainObjects.ArticleContentDO;

import java.util.List;

public class ArticleInfoCO extends BaseEntity {
    String name;
    String description;

    String userID;
    UserInfoCO userInfoCO;

    String discussionID;
    DiscussionInfoCO discussionInfoCO;

    List<String> articleContentIDList;
    List<ArticleContentDO> articleContentDOList;
}
