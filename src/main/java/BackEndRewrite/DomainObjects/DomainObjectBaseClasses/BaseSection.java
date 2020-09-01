package BackEndRewrite.DomainObjects.DomainObjectBaseClasses;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Entity;

/**
 * Used for article Sections
 * and Discussion comments
 */

@Getter@Setter
@Document(collection = "Contents")
public class BaseSection {

    @Id
    ObjectId _id;
    String content;
    ObjectId author;
    Integer upvotes;
    Integer downvotes;
}
