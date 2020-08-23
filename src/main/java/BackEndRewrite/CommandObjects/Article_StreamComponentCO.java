package BackEndRewrite.CommandObjects;

import BackEndRewrite.DomainObjectBaseClasses.BaseEntity;

/**
 * This CO is used to return data needed to LIST an article
 * (e.g. name, description, etc) (metadata)
 * it does not include the content of the article,
 * however, the id can be used to look up the article content
 *
 * It is used on the Index Page, the Profile Page, and Community Page
 *
 */
public class Article_StreamComponentCO extends BaseEntity {
    String name;
    String description;
    String id;

    String userID;
    User_StreamComponentCO userIndexPageCO;
}
