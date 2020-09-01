package BackEndRewrite.DomainObjects.DomainObjectBaseClasses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * Used for article Sections
 * and Discussion comments
 */

@Getter@Setter
@Document(collection = "Contents")
public class BaseSection {


    String Id;
    String content;
    String author;
    Integer upvotes;
    Integer downvotes;
}
