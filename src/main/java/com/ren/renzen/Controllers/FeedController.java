package com.ren.renzen.Controllers;

import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.Converters.CommunityConverter;
import com.ren.renzen.Converters.ProfileConverter;
import com.ren.renzen.ModelAssemblers.ArticleAssembler;
import com.ren.renzen.ModelAssemblers.CommunityAssembler;
import com.ren.renzen.ModelAssemblers.ProfileAssembler;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.ren.renzen.Controllers.Constants.CONTROLLER_PATHS.Article.GET_HOME_STREAMS;

@RestController
//@CrossOrigin("*")
public class FeedController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    //converters
    final ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;

    //controllers
    @Autowired
    public FeedController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler, CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler, CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler) {
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
        this.articleDO_to_articleTabComponentCO = articleDO_to_articleTabComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.articleTabCOAssembler = articleTabCOAssembler;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.profileTabCOAssembler = profileTabCOAssembler;
        this.communityTabCOAssembler = communityTabCOAssembler;
        this.communityStreamCOAssembler = communityStreamCOAssembler;
        this.articleStreamCOAssembler = articleStreamCOAssembler;
    }


    @GetMapping(GET_HOME_STREAMS)
    public ResponseEntity<CollectionModel<?>> getHomeStreams(@PathVariable int page) {

        ArrayList<CollectionModel<?>> returnList = new ArrayList<>();

//        var articleContent = articleService.findAllPage(page);
        var articleContent = articleService.findByIsDraft(false, page);
        var communityContent = communityService.findAllPage();
        var profileContent = userService.findAllPage();

        //return only limited results
        //must be in this order for Javascript Collection Model read
        returnList.add(articleStreamCOAssembler.assembleDomainToPublicModelViewCollection(articleContent));
        returnList.add(profileStreamCOAssembler.assembleDomainToPublicModelViewCollection(profileContent));
        returnList.add(communityStreamCOAssembler.assembleDomainToPublicModelViewCollection(communityContent));

        return ResponseEntity.ok(CollectionModel.wrap(returnList));
    }

}
