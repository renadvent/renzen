package BackEndRewrite.CommandObjects.SubCommunityComponentCOs;

import BackEndRewrite.CommandObjects.ContentCOs.ArticleSectionCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This CO is used to return data needed to render ar article to an article component
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleComponentCO extends RepresentationModel<ArticleComponentCO> {
    String _id;
    ObjectId objectId;

    String name;
    String description;
    ObjectId userID;
    ProfileStreamComponentCO user_streamComponentCO;
    ObjectId discussionID;
    List<ArticleSectionCO> articleSectionCOList = new ArrayList<>();
}
