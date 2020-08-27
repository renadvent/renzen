package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommunityDO_to_CommunityStreamComponentCO implements Converter<CommunityDO, CommunityStreamComponentCO> {


//    final ArticleRepository articleRepository;
//    final UserRepository userRepository;

    //can inject converters with spring?
    final ArticleDO_to_ArticleStreamComponentCO testConverter;
    final ProfileDO_to_ProfileStreamComponentCO ProfileConverter;

    @Autowired
    public CommunityDO_to_CommunityStreamComponentCO(ArticleDO_to_ArticleStreamComponentCO testConverter, ProfileDO_to_ProfileStreamComponentCO profileConverter) {
//        this.articleRepository = articleRepository;
//        this.userRepository = userRepository;
        this.testConverter = testConverter;
        ProfileConverter = profileConverter;
    }

    /**
     * allows for multiple conversions
     *
     * @param sourceList
     * @return
     */
    @Synchronized
    @Nullable
    public List<CommunityStreamComponentCO> convert(Iterable<CommunityDO> sourceList) {
        ArrayList<CommunityStreamComponentCO> communityStreamComponentCOList = new ArrayList<CommunityStreamComponentCO>();
        for (CommunityDO communityDO : sourceList) {
            communityStreamComponentCOList.add(convert(communityDO));
        }
        return communityStreamComponentCOList;
    }

    @Synchronized
    @Nullable
    @Override
    public CommunityStreamComponentCO convert(CommunityDO source) {

        CommunityStreamComponentCO co = new CommunityStreamComponentCO();

        co.setId(source.getId());
        co.setName(source.getName());

        //ProfileDO_to_ProfileStreamComponentCO ProfileConverter = new ProfileDO_to_ProfileStreamComponentCO(discussionRepository);
        for (ProfileDO profile : source.getProfileDOList()) {
            co.getProfileStreamComponentCOList().add(ProfileConverter.convert(profile));
        }

        //not sure why this is necessary...
        //ArticleDO_to_ArticleStreamComponentCO ArticleConverter = new ArticleDO_to_ArticleStreamComponentCO(userRepository);
        for (ArticleDO articleDO : source.getArticleDOList()) {
            //co.getArticleStreamComponentCOList().add(ArticleConverter.convert(articleDO));
            co.getArticleStreamComponentCOList().add(testConverter.convert(articleDO));
        }

        return co;
    }
}
