package BackEndRewrite.CommandObjects.TabComponentCOs;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This Command Object is used to return data to
 * React Application needed to render the main index page at (/index)
 */
@Getter
@Setter
@NoArgsConstructor
public class HomeTabComponentCO extends RepresentationModel<HomeTabComponentCO> {

    String _id;
    ObjectId objectId;

    List<ArticleStreamComponentCO> article_Article_streamComponentCOList = new ArrayList<>();
    List<ProfileStreamComponentCO> user_streamComponentCOList = new ArrayList<>();
    List<CommunityStreamComponentCO> community_streamComponentCOList = new ArrayList<>();
}
