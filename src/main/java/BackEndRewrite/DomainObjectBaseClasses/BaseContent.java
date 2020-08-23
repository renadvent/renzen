package BackEndRewrite.DomainObjectBaseClasses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Used for article Sections
 * and Discussion comments
 */

@Getter@Setter
@Document(collection = "Contents")
public class BaseContent {
    @Id
    String id;
    String content;
    String author;
    Integer upvotes;
    Integer downvotes;
}
