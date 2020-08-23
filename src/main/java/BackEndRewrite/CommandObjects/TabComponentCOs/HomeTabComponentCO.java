package BackEndRewrite.CommandObjects.TabComponentCOs;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the main index page at (/index)
 */
@Getter
@Setter
@NoArgsConstructor
public class HomeTabComponentCO {
    List<ArticleStreamComponentCO> article_Article_streamComponentCOList;
    List<ProfileStreamComponentCO> user_streamComponentCOList;
    List<CommunityStreamComponentCO> community_streamComponentCOList;
}
