package BackEndRewrite.DomainObjects;

import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Data for an Article
 */
@Getter@Setter
@Document(collection = "Articles")
public class ArticleDO {


    @Id
    ObjectId _id;

    String topic;

    String name;
    String description;

    ObjectId userID;
    ObjectId communityID;
    ObjectId discussionID;

    List<ArticleSectionDO> articleSectionDOList;
    //List<String> articleSectionDOIDList;


    public ArticleDO(String name, String description, ObjectId userID, ObjectId communityID,
                     List<ArticleSectionDO> articleSectionDOList){
        this.name=name;
        this.description=description;
        this.userID=userID;
        this.communityID=communityID;
        this.articleSectionDOList=articleSectionDOList;
    }

//    public ArticleDO(String name, String description, ObjectId userID, ObjectId communityID
//                     ){
//        this.name=name;
//        this.description=description;
//        this.userID=userID;
//        this.communityID=communityID;
//        //this.articleSectionDOList=articleSectionDOList;
//    }
}
