package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjectBases.BaseEntity;

import java.util.List;

public class ArticleDO extends BaseEntity {
    String name;
    String description;

    String userID;
    String discussionID;

    List<String> articleContentIDList;

}
