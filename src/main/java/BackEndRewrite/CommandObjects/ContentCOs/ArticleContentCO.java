package BackEndRewrite.CommandObjects.ContentCOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This CO is used to return a section of an article
 */
@Getter@Setter
@NoArgsConstructor
public class ArticleContentCO {
    String id;
    String heading;
    String content;
    String author;
    Integer upvotes;
    Integer downvotes;
}
