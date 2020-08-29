package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


import javax.persistence.Entity;
import java.util.List;

/**
 * Data for an Article
 */
@Getter@Setter
@Document(collection = "Articles")
@NoArgsConstructor //>?
public class ArticleDO {


    @Id
    String Id;

    String name;
    String description;

    String userID;
    String communityID;
    String discussionID;

    List<ArticleSectionDO> articleSectionDOList;
    //List<String> articleSectionDOIDList;


    public ArticleDO(String name, String description, String authorID, String communityID,
                     List<ArticleSectionDO> articleSectionDOList){
        this.name=name;
        this.description=description;
        this.userID=authorID;
        this.communityID=communityID;
        this.articleSectionDOList=articleSectionDOList;
    }
}
