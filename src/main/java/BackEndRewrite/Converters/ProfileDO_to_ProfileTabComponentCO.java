package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileTabComponentCO implements Converter<ProfileDO, ProfileTabComponentCO> {

    @Synchronized
    @Nullable
    @Override
    public ProfileTabComponentCO convert(ProfileDO source){

        final ProfileTabComponentCO co = new ProfileTabComponentCO();

        co.setUsername(source.getUsername());

        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());
        co.setNumberOfDiscussionContentPosts(source.getDiscussionContentIDs().size());

        co.setArticleIDList(source.getArticleIDList());
        co.setCommunityIDList(source.getCommunityIDList());

        co.setDiscussionContentIDList(source.getDiscussionContentIDs());

        return co;
    }
}
