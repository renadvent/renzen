package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.DomainObjectBaseClasses.BaseDomain;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Data for an Article
 */
@Getter@Setter
@Document(collection = "Articles")
@NoArgsConstructor
public class ArticleDO extends BaseDomain {
    String name;
    String description;

    String userID;
    String discussionID;

    List<ArticleSectionDO> articleSectionDOList;
    //List<String> articleSectionDOIDList;
}
