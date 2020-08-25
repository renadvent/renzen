package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjectBaseClasses.BaseEntity;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Data for an Article
 */
@Getter@Setter
@Document(collection = "Articles")
public class ArticleDO extends BaseEntity {
    String name;
    String description;

    String userID;
    String discussionID;

    List<ArticleSectionDO> articleSectionDOList;
    //List<String> articleSectionDOIDList;
}
