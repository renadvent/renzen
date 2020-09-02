package BackEndRewrite.CommandObjects.StreamComponentCOs;

import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * This CO is used to return data to LIST communties
 */
@Getter
@Setter
@NoArgsConstructor
public class CommunityStreamComponentCO extends RepresentationModel<CommunityStreamComponentCO> {
    String _id;
    ObjectId objectId;

    String name;
    List<ProfileStreamComponentCO> profileStreamComponentCOList = new ArrayList<>();
    List<ArticleStreamComponentCO> articleStreamComponentCOList = new ArrayList<>();
}
