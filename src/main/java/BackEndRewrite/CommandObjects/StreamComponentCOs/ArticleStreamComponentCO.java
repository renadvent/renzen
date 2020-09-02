package BackEndRewrite.CommandObjects.StreamComponentCOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.RepresentationModel;

/**
 * This CO is used to return data needed to LIST an article
 * (e.g. name, description, etc) (metadata)
 * it does not include the content of the article,
 * however, the id can be used to look up the article content
 *
 * It is used on the Index Page, the Profile Page, and Community Page
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleStreamComponentCO extends RepresentationModel<ArticleStreamComponentCO> {
    String _id;

    String name;
    String description;
    String authorID;//author
    String authorName; //implement this in converter
    ProfileStreamComponentCO profileStreamComponentCO;
}
