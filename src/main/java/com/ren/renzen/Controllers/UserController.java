package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ProfileStreamComponentCO;
import com.ren.renzen.CommandObjects.ProfileTabComponentCO;
import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.DiscussionService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

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

    public UserController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
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

    @RequestMapping(path="/register")
    public Object Register(@Valid @RequestBody ProfileDO profileDO, BindingResult result){

        if (result.hasErrors()){
            return userService.errorMap(result);
        }

        if (userService.checkIfUsernameTaken(profileDO.getUsername())){
            throw new RuntimeException("user name taken");
        }else{
            return profileTabCOAssembler.toModel(userService.save(new ProfileDO(profileDO.getUsername(),profileDO.getUsername())));
        }
    }

    @RequestMapping(path="/login")
    public ResponseEntity<ProfileTabComponentCO> Login(@RequestBody SiteController.SitePayloads.UserNamePassword payload){
        return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.findProfileDOByNameAndPassword(payload.username, payload.password)));
    }



    @GetMapping(path="/getProfiles")
    public ResponseEntity<CollectionModel<?>> getAllProfiles(){
        return ResponseEntity
                .ok(profileStreamCOAssembler.toCollectionModel(userService.findAll()));
    }

    @GetMapping(path="/getProfileStreamComponentCO/{id}")
    public ProfileStreamComponentCO getProfileStreamComponentCO(@PathVariable ObjectId id){
        return profileStreamCOAssembler.toModel(userService.findBy_id(id));
    }

    @RequestMapping(path="/profileTabComponentCO/{id}")
    public ResponseEntity<ProfileTabComponentCO> getProfileTabComponentCO(@PathVariable ObjectId id){
        return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.findBy_id(id)));
    }
}
