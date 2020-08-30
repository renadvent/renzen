package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.*;
import BackEndRewrite.ModelAssemblers.ProfileStreamCOAssembler;
import BackEndRewrite.ModelAssemblers.ProfileTabCOAssembler;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;

    public TabControllers(ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, UserService userService, ArticleService articleService, CommunityService communityService, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler) {
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
        this.articleDO_to_articleComponentCO = articleDO_to_articleComponentCO;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.profileTabCOAssembler = profileTabCOAssembler;
    }

    /**
     * Get Profile Tab Component CO for React
     * @param id
     * @return
     */
    @RequestMapping(path="/profiles/profileTabComponentCO/{id}")
    public ResponseEntity<?> getProfileTabComponentCO(@PathVariable String id){

        //get Command Object
        ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convert(userService.findProfileDOById(id));

        //convert Command Object to model
        EntityModel<ProfileTabComponentCO> entityModel = profileTabCOAssembler.toModel(profileTabComponentCO);

        return ResponseEntity.ok(entityModel);

    }

    /**
     * Get Community Tab Component CO for React
     * @param id
     * @return
     */
    @RequestMapping(path="/communities/communityTabComponent/{id}")
    @ResponseBody
    public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable String id){

        //get command object
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(communityService.findCommunityDOById(id));

        //TODO convert command object to model

        return ResponseEntity
                .ok(communityTabComponentCO);
    }
}
