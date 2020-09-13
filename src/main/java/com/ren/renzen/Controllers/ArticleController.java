package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ArticleStreamComponentCO;
import com.ren.renzen.CommandObjects.ArticleTabComponentCO;
import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.DiscussionService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ArticleController {


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

    public ArticleController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
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


    @PostMapping(path = "/createArticle")
    public ResponseEntity<?> createArticle(@RequestBody ArticleDO articleDO) {

        //check if provided ids exist
        ProfileDO profileDO = userService.findBy_id(articleDO.getUserID());

        CommunityDO communityDO = communityService.findBy_id(articleDO.getCommunityID());

        //save ArticleDO to get an ID from mongodb for it
        ArticleDO savedArticleDO = articleService.save(articleDO);

        //add article to user
        profileDO.getArticleIDList().add(savedArticleDO.get_id());
        userService.save(profileDO);

        //add article to community
        communityDO.getArticleDOList().add(savedArticleDO.get_id());
        communityService.save(communityDO);

        return ResponseEntity.ok(articleTabCOAssembler.toModel(savedArticleDO));
        //return ResponseEntity.ok(articleComponentCOAssembler.toModel(articleService.findBy_id(savedArticleDO.get_id())));

    }


    @GetMapping("/getArticles")
    public ResponseEntity<CollectionModel<?>> getAllArticles(){

        List<ArticleStreamComponentCO> returnList = new ArrayList<>();
        for (ArticleDO articleDO : articleService.findAll()){
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }
        return ResponseEntity.ok(CollectionModel.of(returnList));
    }


    //TODO update toModel
    @GetMapping(path="/getArticleStreamComponentCO/{id}")
    public ArticleStreamComponentCO getArticleStreamComponentCO(@PathVariable ObjectId id){
        return articleStreamCOAssembler.toModel(articleService.findBy_id(id));
    }

    //TODO update toModel
    @GetMapping(path="/getArticleTabComponentCO/{id}")
    public ArticleTabComponentCO getArticleTabComponentCO(@PathVariable ObjectId id){
        return articleTabCOAssembler.toModel(articleService.findBy_id(id));
    }





}
