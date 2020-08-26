package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.DomainObjects.CommunityDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommunityDO_to_CommunityTabComponentCO implements Converter<CommunityDO, CommunityTabComponentCO> {

    final ArticleDO_to_ArticleStreamComponentCO articleConverter;
    final ProfileDO_to_ProfileStreamComponentCO profileConverter;
    final DiscussionDO_to_DiscussionComponentCO discussionConverter;

    public CommunityDO_to_CommunityTabComponentCO(ArticleDO_to_ArticleStreamComponentCO articleConverter, ProfileDO_to_ProfileStreamComponentCO profileConverter, DiscussionDO_to_DiscussionComponentCO discussionConverter) {
        this.articleConverter = articleConverter;
        this.profileConverter = profileConverter;
        this.discussionConverter = discussionConverter;
    }

    /**
     * Should Discussion section be sent seperately???? not right now
     *
     * @param source
     * @return
     */
    @Synchronized
    @Nullable
    @Override
    public CommunityTabComponentCO convert(CommunityDO source) {

        CommunityTabComponentCO co = new CommunityTabComponentCO();

        co.setArticle_Article_streamComponentCOList(articleConverter.convert(source.getArticleDOList()));
        co.setNumberOfArticles(source.getArticleDOList().size());

        co.setUser_streamComponentCOList(profileConverter.convert(source.getProfileDOList()));
        co.setNumberOfArticles(source.getProfileDOList().size());

        /**
         * here id lookup is done by converter
         */
        co.setDiscussionDiscussionComponentCO(discussionConverter.convert(source.getDiscussionID()));

        return co;
    }
}
