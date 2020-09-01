package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.Converters.ArticleDO_to_ArticleStreamComponentCO;
import BackEndRewrite.Converters.CommunityDO_to_CommunityStreamComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileStreamComponentCO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.CommunityRepository;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//TODO not supposed to directly access repository from controllers... supposed to use services

@RestController
public class StreamControllers {


    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    final UserRepository userRepository;
    final ArticleRepository articleRepository;
    final CommunityRepository communityRepository;

    public StreamControllers(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, UserService userService, ArticleService articleService, CommunityService communityService, UserRepository userRepository, ArticleRepository articleRepository, CommunityRepository communityRepository) {
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.communityRepository = communityRepository;
    }

    @RequestMapping(path="/getProfiles")
    @ResponseBody
    public Iterable<ProfileDO> getAllProfiles(){
        return userRepository.findAll();
    }

    /**
     * returns a profile stream component for react render
     * @param id
     * @return
     */
    @RequestMapping(path="/profiles/profileStreamComponentCO/{id}")
    @ResponseBody
    public ProfileStreamComponentCO  getProfileStreamComponentCO(@PathVariable ObjectId id){
        return userRepository.findById(id)
                .map(profileDO_to_profileStreamComponentCO::convert)
                        .orElse(null);
    }

    /**
     * returns an article stream component for react render
     * @param id
     * @return
     */
    @RequestMapping(path="/articles/articleStreamComponentCO/{id}")
    @ResponseBody
    public ArticleStreamComponentCO getArticleStreamComponentCO(@PathVariable ObjectId id){
        return articleRepository.findById(id)
                .map(articleDO_to_articleStreamComponentCO::convert)
                .orElse(null);
    }

    /**
     * returns a community stream component for react render
     * @param id
     * @return
     */
    @RequestMapping(path="/communities/communityStreamComponentCO/{id}")
    @ResponseBody
    public CommunityStreamComponentCO getCommunityStreamComponentCO(@PathVariable ObjectId id){
        return communityRepository.findById(id)
                .map(communityDO_to_communityStreamComponentCO::convert)
                .orElse(null);
    }
}
