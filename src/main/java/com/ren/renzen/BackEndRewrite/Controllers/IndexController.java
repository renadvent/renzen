package com.ren.renzen.BackEndRewrite.Controllers;

import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs.ArticleTabComponentCO;
import com.ren.renzen.BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import com.ren.renzen.BackEndRewrite.Converters.*;
import com.ren.renzen.BackEndRewrite.DomainObjects.ArticleDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.CommunityDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.DiscussionDO;
import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import com.ren.renzen.BackEndRewrite.ModelAssemblers.*;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.ArticleService;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.CommunityService;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.DiscussionService;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.UserService;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class IndexController {

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
    public IndexController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
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

    //-------------------------------------------JOIN

    @PostMapping(path="/joinCommunity")
    public ResponseEntity<?> joinCommunity(@RequestBody JoinCommunityPayload payload){

        var profileDO=userService.findBy_id(payload.userId);
        var communityDO = communityService.findBy_id(payload.communityId);

        profileDO.getCommunityIDList().add(communityDO.get_id());
        communityDO.getProfileDOList().add(profileDO.get_id());

        userService.save(profileDO);
        communityService.save(communityDO);

        //?
        return ResponseEntity.ok(null);
    }

    @Getter@Setter
    static class JoinCommunityPayload{
        ObjectId userId;
        ObjectId communityId;
    }

    //-------------------------------------------CREATE
    @PostMapping(path="/createCommunity")
    public ResponseEntity<?> createCommunity(@RequestBody CommunityDO communityDO){

        //check if community name already exists
        if (communityService.checkIfCommunityNameUsed(communityDO.getName())){
            throw new RuntimeException("Community Name already in use");
        }

        //create new discussion for this community
        DiscussionDO discussionDO=discussionService.save(new DiscussionDO());

        //add discussion id to community
        communityDO.setDiscussionID(discussionDO.get_id());
        //communityDO.getProfileDOList().add()
        discussionService.save(discussionDO);

        //save community
        communityDO = communityService.save(communityDO);

        ProfileDO profileDO = userService.findBy_id(communityDO.getCreatorID());
        profileDO.getCommunityIDList().add(communityDO.get_id());
        userService.save(profileDO);

        return ResponseEntity.ok(communityTabCOAssembler.toModel(communityDO));
//        return ResponseEntity
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//                .body(entityModel);
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

    @Getter@Setter
    static class createArticlePayload{

    }

    //-----------------------------------------------BASIC AUTHENTICATION

    @RequestMapping(path="/login")
    public ResponseEntity<ProfileTabComponentCO> Login(@RequestBody SitePayloads.UserNamePassword payload){
        return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.findProfileDOByNameAndPassword(payload.username, payload.password)));
    }

    @RequestMapping(path="/register")
    public ResponseEntity<ProfileTabComponentCO> Register(@RequestBody SitePayloads.UserNamePassword payload){
        if (userService.checkIfUsernameTaken(payload.username)){
            throw new RuntimeException("user name taken");
        }else{
            return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.save(new ProfileDO(payload.username,payload.password))));
        }
    }

    //---------------------------------------------------------

    @GetMapping(path="/getHomeStreams")
    public ResponseEntity<CollectionModel<?>> getHomeStreams(){

        ArrayList<CollectionModel<?>> returnList = new ArrayList<>();

        //TODO convert
        returnList.add(articleStreamCOAssembler.toCollectionModel(articleService.findAll()));
        returnList.add(profileStreamCOAssembler.toCollectionModel(userService.findAll()));
        returnList.add(communityStreamCOAssembler.toCollectionModel(communityService.findAll()));

        return ResponseEntity.ok(CollectionModel.of(returnList));
    }

    //------------------------------------------------GET

    @GetMapping(path="/getProfiles")
    public ResponseEntity<CollectionModel<?>> getAllProfiles(){
        return ResponseEntity
                .ok(profileStreamCOAssembler.toCollectionModel(userService.findAll()));
    }

    @GetMapping("/getArticles")
    public ResponseEntity<CollectionModel<?>> getAllArticles(){

        List<ArticleStreamComponentCO> returnList = new ArrayList<>();
        for (ArticleDO articleDO : articleService.findAll()){
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }
        return ResponseEntity.ok(CollectionModel.of(returnList));
    }

    @GetMapping("/getCommunities")
    public ResponseEntity<CollectionModel<?>> getAllCommunities(){
        return ResponseEntity
                .ok(communityStreamCOAssembler.toCollectionModel(communityService.findAll()));
    }

    @GetMapping(path="/getAllByCommunityIDAndTopic")
    public List<ArticleStreamComponentCO> getAllByCommunityIDAndTopic(@RequestBody getAllByCommunityIDAndTopicPayload payload){

        List<ArticleStreamComponentCO> returnList = new ArrayList<>();

        for (ArticleDO articleDO : articleService.findAllByCommunityIDAndTopic(payload.communityID,payload.topic)){
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }
        return returnList;
    }

    //------------------------------------------------By ID

    @GetMapping(path="/getProfileStreamComponentCO/{id}")
    public ProfileStreamComponentCO getProfileStreamComponentCO(@PathVariable ObjectId id){
        return profileDO_to_profileStreamComponentCO.convert(userService.findBy_id(id));
    }

    @RequestMapping(path="/profileTabComponentCO/{id}")
    public ResponseEntity<ProfileTabComponentCO> getProfileTabComponentCO(@PathVariable ObjectId id){
        return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.findBy_id(id)));
    }

    @GetMapping(path="/getArticleStreamComponentCO/{id}")
    public ArticleStreamComponentCO getArticleStreamComponentCO(@PathVariable ObjectId id){
        return articleDO_to_articleStreamComponentCO.convert(articleService.findBy_id(id));
    }

    @GetMapping(path="/getArticleTabComponentCO/{id}")
    public ArticleTabComponentCO getArticleTabComponentCO(@PathVariable ObjectId id){
        return articleDO_to_articleTabComponentCO.convert(articleService.findBy_id(id));
    }

    @GetMapping(path="/getCommunityStreamComponentCO/{id}")
    public CommunityStreamComponentCO getCommunityStreamComponentCO(@PathVariable ObjectId id){
        return communityDO_to_communityStreamComponentCO.convert(communityService.findBy_id(id));
    }

    @RequestMapping(path="/communityTabComponent/{id}")
    public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable("id") ObjectId id){
        return ResponseEntity
                .ok(communityTabCOAssembler.toModel(communityService.findBy_id(id)));
    }

    //------------------------------------------------Payloads

//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public static class CreateArticlePayload {
//        String name;
//        String description;
//        ObjectId authorID;
//        ObjectId communityID;
//        String topic;
//
//        List<ArticleSectionDO> articleSectionDOList=new ArrayList<>();
//
//        public CreateArticlePayload(String name,String description,ObjectId authorID,ObjectId communityID,
//                                    String topic){
//            this.name=name;
//            this.description=description;
//            this.authorID=authorID;
//            this.communityID=communityID;
//            this.topic=topic;
//        }
//
//        public CreateArticlePayload(String name,String description,ObjectId authorID,ObjectId communityID
//                ,List<ArticleSectionDO> articleSectionDOList){
//            this.name=name;
//            this.description=description;
//            this.authorID=authorID;
//            this.communityID=communityID;
//            this.articleSectionDOList=articleSectionDOList;
//        }
//
//    }

    @Getter@Setter
    static class getAllByCommunityIDAndTopicPayload{
        ObjectId communityID;
        String topic;
        public getAllByCommunityIDAndTopicPayload(ObjectId communityID,String topic){
            this.communityID=communityID;
            this.topic=topic;
        }
    }

}
