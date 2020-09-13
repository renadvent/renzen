package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ArticleStreamComponentCO;
import com.ren.renzen.CommandObjects.CommunityStreamComponentCO;
import com.ren.renzen.CommandObjects.ProfileStreamComponentCO;
import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.CommandObjects.ProfileTabComponentCO;
import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.DomainObjects.DiscussionDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.DiscussionService;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin("*")
public class SiteController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final DiscussionService discussionService;
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
    public SiteController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
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

    //TODO implement
    @GetMapping (path="/getSpotlight")
    ResponseEntity<?> getSpotlight(){
//        var articleContent = articleService.findAllPage();
//        var communityContent = communityService.findAllPage();
        return null;
        //return ResponseEntity.ok(CollectionModel.of(articleContent,communityContent);
    }

    @PostMapping(path="/addBookmark")
    public ResponseEntity<?> addBookmark(@RequestBody addBookmarkPayload payload){

        var profileDO = userService.findBy_id(payload.getUserId());
        var articleDO = articleService.findBy_id(payload.getArticleId());

        profileDO.getArticleBookmarkIDList().add(articleDO.get_id());
        userService.save(profileDO);

        return ResponseEntity.ok(null);
    }

    @Getter@Setter
    static class addBookmarkPayload{
        ObjectId userId;
        ObjectId articleId;
    }

    @GetMapping(path="/getHomeStreams")
    public ResponseEntity<CollectionModel<?>> getHomeStreams(){

        ArrayList<CollectionModel<?>> returnList = new ArrayList<>();

        var articleContent = articleService.findAllPage();
        var communityContent = communityService.findAllPage();
        var profileContent = userService.findAllPage();

        //return only limited results
        //must be in this order for Javascript Collection Model read
        returnList.add(articleStreamCOAssembler.toCollectionModel(articleContent));
        returnList.add(profileStreamCOAssembler.toCollectionModel(profileContent));
        returnList.add(communityStreamCOAssembler.toCollectionModel(communityContent));

        return ResponseEntity.ok(CollectionModel.of(returnList));
    }

    @Getter
    @Setter@NoArgsConstructor
    public static class SitePayloads {
        static class UserNamePassword{
            String username;
            String password;

            public UserNamePassword(String username,String password){
                this.username=username;
                this.password=password;
            }
        }
    }
}
