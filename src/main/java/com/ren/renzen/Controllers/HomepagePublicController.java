package com.ren.renzen.Controllers;

import com.ren.renzen.Converters.*;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Payload.addBookmarkPayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.GET_HOME_STREAMS;
import static com.ren.renzen.Controllers.CONTROLLER_PATHS.User.ADD_BOOKMARK;

@RestController
//@CrossOrigin("*")
public class HomepagePublicController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    //converters
    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;

    //controllers
    @Autowired
    public HomepagePublicController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
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

    @PostMapping(ADD_BOOKMARK)
    public ResponseEntity<?> addBookmark(@RequestBody @Valid addBookmarkPayload payload, Principal principal) {

        //var profileDO = userService.findBy_id(payload.getUserId());
        var profileDO = userService.findByUsername(principal.getName());

        var articleDO = articleService.findBy_id(payload.getArticleId());

        profileDO.getArticleBookmarkIDList().add(articleDO.get_id());
        userService.update(profileDO);

        return ResponseEntity.ok(null);
    }

    @GetMapping(GET_HOME_STREAMS)
    public ResponseEntity<CollectionModel<?>> getHomeStreams(@PathVariable int page) {

        ArrayList<CollectionModel<?>> returnList = new ArrayList<>();

        var articleContent = articleService.findAllPage(page);
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
