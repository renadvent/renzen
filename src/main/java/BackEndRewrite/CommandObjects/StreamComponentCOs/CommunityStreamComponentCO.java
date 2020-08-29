package BackEndRewrite.CommandObjects.StreamComponentCOs;

import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * This CO is used to return data to LIST communties
 */
@Getter
@Setter
@NoArgsConstructor
public class CommunityStreamComponentCO extends RepresentationModel<CommunityStreamComponentCO> {

    String id;
    String name;
    List<ProfileStreamComponentCO> profileStreamComponentCOList;
    List<ArticleStreamComponentCO> articleStreamComponentCOList;

}
