package BackEndRewrite.CommandObjects.ContentCOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 * This CO is used to return a section of an article
 */
@Getter@Setter
@NoArgsConstructor
public class ArticleSectionCO {
    String _id;
    ObjectId objectId; // needed for assemblers

    String heading;
    String content;
    ObjectId authorID;
    Integer upvotes;
    Integer downvotes;
}
