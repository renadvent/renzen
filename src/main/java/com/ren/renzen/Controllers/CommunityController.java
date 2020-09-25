package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.CommunityStreamComponentCO;
import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.DomainObjects.DiscussionDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.DiscussionService;
import com.ren.renzen.Services.Interfaces.UserService;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommunityController {


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

    public CommunityController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler) {
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


    @PostMapping(path = "/joinCommunity")
    public ResponseEntity<?> joinCommunity(@RequestBody JoinCommunityPayload payload) {

        var profileDO = userService.findBy_id(payload.userId);
        var communityDO = communityService.findBy_id(payload.communityId);

        profileDO.getCommunityIDList().add(communityDO.get_id());
        communityDO.getProfileDOList().add(profileDO.get_id());

        userService.save(profileDO);
        communityService.save(communityDO);

        //?
        return ResponseEntity.ok(null);
    }

    @PostMapping(path = "/createCommunity")
    public ResponseEntity<?> createCommunity(@RequestBody CommunityDO communityDO) {

        //check if community name already exists
        if (communityService.checkIfCommunityNameUsed(communityDO.getName())) {
            throw new RuntimeException("Community Name already in use");
        }

        //create new discussion for this community
        DiscussionDO discussionDO = discussionService.save(new DiscussionDO());

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

    @GetMapping("/getCommunities")
    public CollectionModel<CommunityStreamComponentCO> getAllCommunities() {
        return (communityStreamCOAssembler.toCollectionModel(communityService.findAll()));
    }

    //TODO update toModel
    @GetMapping(path = "/getCommunityStreamComponentCO/{id}")
    public CommunityStreamComponentCO getCommunityStreamComponentCO(@PathVariable ObjectId id) {
        return communityStreamCOAssembler.toModel(communityService.findBy_id(id));
    }

//    @GetMapping("/getCommunities")
//    public ResponseEntity<CollectionModel<RepresentationModel<?>>> getAllCommunities(){
//        return ResponseEntity
//                .ok(communityStreamCOAssembler.toCollectionModel(communityService.findAll()));
//    }


//    @GetMapping(path="/getAllByCommunityIDAndTopic")
//    public List<ArticleStreamComponentCO> getAllByCommunityIDAndTopic(@RequestBody getAllByCommunityIDAndTopicPayload payload){
//
//        List<ArticleStreamComponentCO> returnList = new ArrayList<>();
//
//        for (ArticleDO articleDO : articleService.findAllByCommunityIDAndTopic(payload.communityID,payload.topic)){
//            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
//        }
//        return returnList;
//    }

    //TODO update toModel
    @RequestMapping(path = "/communityTabComponent/{id}")
    public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable("id") ObjectId id) {
        return ResponseEntity
                .ok(communityTabCOAssembler.toModel(communityService.findBy_id(id)));
    }

    @Getter
    @Setter
    static class JoinCommunityPayload {
        ObjectId userId;
        ObjectId communityId;
    }

    @Getter
    @Setter
    static class getAllByCommunityIDAndTopicPayload {
        ObjectId communityID;
        String topic;

        public getAllByCommunityIDAndTopicPayload(ObjectId communityID, String topic) {
            this.communityID = communityID;
            this.topic = topic;
        }
    }


}
