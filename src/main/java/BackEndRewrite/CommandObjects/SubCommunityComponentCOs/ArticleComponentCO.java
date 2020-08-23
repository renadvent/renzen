package BackEndRewrite.CommandObjects.SubCommunityComponentCOs;

import BackEndRewrite.CommandObjects.ContentCOs.ArticleContentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This CO is used to return data needed to render ar article to an article component
 */
@Getter
@Setter
@NoArgsConstructor
public class ArticleComponentCO {

    String name;
    String description;
    String id;

    String userID;
    ProfileStreamComponentCO user_streamComponentCO;

    String discussionID;
    //DiscussionInfoCO discussionInfoCO;

    List<String> articleContentIDList;
    List<ArticleContentCO> articleContentCOList;

}
