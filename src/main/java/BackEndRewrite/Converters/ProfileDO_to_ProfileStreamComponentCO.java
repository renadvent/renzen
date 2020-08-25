package BackEndRewrite.Converters;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import com.mongodb.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileDO_to_ProfileStreamComponentCO implements Converter<ProfileDO,ProfileStreamComponentCO> {
//
//    final DiscussionRepository discussionRepository;
//
//    @Autowired
//    public ProfileDO_to_ProfileStreamComponentCO(DiscussionRepository discussionRepository) {
//        this.discussionRepository = discussionRepository;
//    }
//
//    final CommunityDO_to_CommunityStreamComponentCO communityConverter;
//
//    public ProfileDO_to_ProfileStreamComponentCO(CommunityDO_to_CommunityStreamComponentCO communityConverter) {
//        this.communityConverter = communityConverter;
//    }

    @Synchronized
    @Nullable
    @Override
    public ProfileStreamComponentCO convert(ProfileDO source){

        final ProfileStreamComponentCO co = new ProfileStreamComponentCO();

        co.setUsername(source.getUsername());
        co.setNumberOfArticles(source.getArticleIDList().size());
        co.setNumberOfCommunities(source.getCommunityIDList().size());

        co.setArticleIDList(source.getArticleIDList());
        co.setCommunityIDList(source.getCommunityIDList());








        //discussionRepository.fin




        //
        //co.setNumberOfDiscussionContentPosts();

        return co;

    }
}
