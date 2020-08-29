package BackEndRewrite.CommandObjects.SubCommunityComponentCOs;

import BackEndRewrite.CommandObjects.ContentCOs.ArticleSectionCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * This CO is used to return data needed to render ar article to an article component
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleComponentCO extends RepresentationModel<ArticleComponentCO> {

    String name;
    String description;
    String id;

    String userID;
    ProfileStreamComponentCO user_streamComponentCO;

    String discussionID;
    //DiscussionInfoCO discussionInfoCO;

    //List<String> articleContentIDList;
    List<ArticleSectionCO> articleSectionCOList;

}
