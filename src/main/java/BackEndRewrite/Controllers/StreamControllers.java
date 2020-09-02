package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.Converters.ArticleDO_to_ArticleStreamComponentCO;
import BackEndRewrite.Converters.CommunityDO_to_CommunityStreamComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileStreamComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//TODO not supposed to directly access repository from controllers... supposed to use services

@CrossOrigin("*")
@RestController
public class StreamControllers {


    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    public StreamControllers(ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, UserService userService, ArticleService articleService, CommunityService communityService) {
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
    }

    @GetMapping(path="/getProfiles")
    public List<ProfileStreamComponentCO>  getAllProfiles(){
        List<ProfileStreamComponentCO> returnList = new ArrayList<>();
        for (ProfileDO profileDO : userService.findAll()){
            returnList.add(profileDO_to_profileStreamComponentCO.convert(profileDO));
        }
        return returnList;
    }

    @GetMapping(path="/getCommunitiesByProfile/{id}")
    public List<CommunityStreamComponentCO> getCommunitiesByProfile(@PathVariable ObjectId id){

        List<CommunityStreamComponentCO> returnList = new ArrayList<>();

        for (CommunityDO communityDO : communityService.findByCreatorID(id)){
            returnList.add(communityDO_to_communityStreamComponentCO.convert(communityDO));
        }
        return returnList;
    }

    @GetMapping("/getArticles")
    public List<ArticleStreamComponentCO> getAllArticles(){
        List<ArticleStreamComponentCO> returnList = new ArrayList<>();
        for (ArticleDO articleDO : articleService.findAll()){
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }
        return returnList;
    }

    @GetMapping("/getCommunities")
    public List<CommunityStreamComponentCO> getAllCommunities(){
        List<CommunityStreamComponentCO> returnList = new ArrayList<>();
        for (CommunityDO communityDO : communityService.findAll()){
            returnList.add(communityDO_to_communityStreamComponentCO.convert(communityDO));
        }
        return returnList;
    }

    /**
     * returns a profile stream component for react render
     * @param id
     * @return
     */
    @GetMapping(path="/getProfileStreamComponentCO/{id}")
    public ProfileStreamComponentCO  getProfileStreamComponentCO(@PathVariable ObjectId id){
        return profileDO_to_profileStreamComponentCO.convert(userService.findBy_id(id));
    }

    /**
     * returns an article stream component for react render
     * @param id
     * @return
     */
    @GetMapping(path="/getArticleStreamComponentCO/{id}")
    public ArticleStreamComponentCO getArticleStreamComponentCO(@PathVariable ObjectId id){
        return articleDO_to_articleStreamComponentCO.convert(articleService.findBy_id(id));
    }

    /**
     * returns a community stream component for react render
     * @param id
     * @return
     */
    @GetMapping(path="/getCommunityStreamComponentCO/{id}")
    public CommunityStreamComponentCO getCommunityStreamComponentCO(@PathVariable ObjectId id){
        return communityDO_to_communityStreamComponentCO.convert(communityService.findBy_id(id));
    }
}
