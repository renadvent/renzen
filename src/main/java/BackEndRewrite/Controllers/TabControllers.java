package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.*;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.CommunityRepository;
import BackEndRewrite.Repositories.UserRepository;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TabControllers {

    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;

    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    final UserRepository userRepository;
    final ArticleRepository articleRepository;
    final CommunityRepository communityRepository;

    public TabControllers(ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, UserService userService, ArticleService articleService, CommunityService communityService, UserRepository userRepository, ArticleRepository articleRepository, CommunityRepository communityRepository) {
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
        this.articleDO_to_articleComponentCO = articleDO_to_articleComponentCO;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
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


    @RequestMapping(path="/profiles/profileTabComponentCO/{id}")
    @ResponseBody
    public ProfileTabComponentCO getProfileTabComponentCO(@PathVariable String id){
        return userRepository.findById(id)
                .map(profileDO_to_profileTabComponentCO::convert)
                .orElse(null);
    }

    @RequestMapping(path="/communities/communityTabComponent/{id}")
    @ResponseBody
    public CommunityTabComponentCO getCommunityTabComponentCO(@PathVariable String id){
        return communityRepository.findById(id)
                .map(communityDO_to_communityTabComponentCO::convert)
                .orElse(null);
    }



}
