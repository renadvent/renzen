package com.ren.renzen.Controllers;

import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.Converters.CommunityConverter;
import com.ren.renzen.Converters.ProfileConverter;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.ResourceObjects.DomainObjects.CommunityDO;
import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import com.ren.renzen.ResourceObjects.Payload.CreateCommunityPayload;
import com.ren.renzen.ResourceObjects.Payload.JoinCommunityPayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Community.*;

public class CommunityController {
    @RestController
    public static class CommunityEditorController {


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
        final ArticleTabCOAssembler articleTabCOAssembler;
        final ProfileStreamCOAssembler profileStreamCOAssembler;
        final ProfileTabCOAssembler profileTabCOAssembler;
        final CommunityTabCOAssembler communityTabCOAssembler;
        final CommunityStreamCOAssembler communityStreamCOAssembler;
        final ArticleStreamCOAssembler articleStreamCOAssembler;

        //
        final MapValidationErrorService mapValidationErrorService;

        public CommunityEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
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
            this.mapValidationErrorService = mapValidationErrorService;
        }

        //@PostMapping(path="/deleteCommunity"


        @PostMapping(JOIN_COMMUNITY)
        public ResponseEntity<?> joinCommunity(@RequestBody JoinCommunityPayload payload, BindingResult result, Principal principal) {

            //CHECK BINDING RESULTS OF PAYLOAD
            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
            if (errorMap != null) return errorMap;

            //ADD LOGGED IN USER TO COMMUNITY
            var profileDO = userService.findByUsername(principal.getName());
            var communityDO = communityService.findBy_id(payload.getCommunityId());

            profileDO.getJoinedCommunityIDList().add(communityDO.get_id());
            communityDO.getProfileDOList().add(profileDO.get_id());
            communityDO.getUpdated_at().add(new Date());


            userService.update(profileDO);
            communityService.saveOrUpdateCommunity(communityDO, principal.getName());

            return ResponseEntity.ok("success");
        }

        @PostMapping(CREATE_COMMUNITY)
        public ResponseEntity<?> createCommunity(@Valid @RequestBody CreateCommunityPayload payload, BindingResult bindingResult,
                                                 Principal principal) {

            System.out.println("create community");

            ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
            if (errorMap != null) return errorMap;

            //check if community name already exists
            if (communityService.checkIfCommunityNameUsed(payload.getName())) {
                throw new RuntimeException("Community Name already in use");
            }

            //save community
            var communityDO = new CommunityDO();
            communityDO.setName(payload.getName());
            communityDO.setCreatorName(principal.getName());

            communityDO.setCreated_at(new Date());
            communityDO.getUpdated_at().add(new Date());


            communityDO.setCreatorID(userService.findByUsername(principal.getName()).get_id());
            //communityDO.setCreatorID(userService.findByUsername(payload.getName()).get_id());

            communityService.save(communityDO);

            ProfileDO profileDO = userService.findBy_id(communityDO.getCreatorID());
            profileDO.getJoinedCommunityIDList().add(communityDO.get_id());
            userService.update(profileDO);

            return ResponseEntity.ok(communityTabCOAssembler.assembleDomainToFullModelView(communityDO));
        }


    }

    @RestController
    public static class CommunityViewerController {

        //SERVICES
        final CommunityService communityService;
        final UserService userService;

        //ASSEMBLERS
        final CommunityStreamCOAssembler communityStreamCOAssembler;
        final CommunityTabCOAssembler communityTabCOAssembler;

        public CommunityViewerController(CommunityService communityService, UserService userService, CommunityStreamCOAssembler communityStreamCOAssembler, CommunityTabCOAssembler communityTabCOAssembler) {
            this.communityService = communityService;
            this.userService = userService;
            this.communityStreamCOAssembler = communityStreamCOAssembler;
            this.communityTabCOAssembler = communityTabCOAssembler;
        }

        //TODO update toModel
        @GetMapping(GET_COMMUNITY_STREAM_COMPONENT)
        public ResponseEntity<?> getCommunityStreamComponentCO(@PathVariable ObjectId id, Principal principal) {

            var communityDO = communityService.findBy_id(id);

            if (principal == null || !principal.getName().equals(communityDO.getCreatorName())) {
                //GET PUBLIC VERSION
                return getPublicCommunityStreamComponentCO(id);
                //return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToPublicModelView(communityDO));
            } else {
                //GET FULL VERSION
                return getFullCommunityStreamComponentCO(id);
                //ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToFullModelView(communityDO));
            }
        }

        public ResponseEntity<?> getPublicCommunityStreamComponentCO(ObjectId id) {
            var communityDO = communityService.findBy_id(id);
            //GET PUBLIC VERSION
            return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToPublicModelView(communityDO));
        }

        public ResponseEntity<?> getFullCommunityStreamComponentCO(ObjectId id) {
            var communityDO = communityService.findBy_id(id);
            //GET FULL VERSION
            return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToFullModelView(communityDO));
        }


        //TODO update toModel
        @GetMapping(GET_COMMUNITY_TAB_COMPONENT)
        public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable("id") ObjectId id, Principal principal) {
            var communityDO = communityService.findBy_id(id);

            if (principal == null || !principal.getName().equals(communityDO.getCreatorName())) {
                //GET PUBLIC VERSION
                return ResponseEntity.ok(communityTabCOAssembler.assembleDomainToPublicModelView(communityDO));
            } else {
                //GET FULL VERSION
                return ResponseEntity.ok(communityTabCOAssembler.assembleDomainToFullModelView(communityDO));
            }
        }


    //    public ResponseEntity<?> getPublicCommunityTabComponentCO(ObjectId id) {
    //        var communityDO = communityService.findBy_id(id);
    //        //GET PUBLIC VERSION
    //        return ResponseEntity.ok(communityTabCOAssembler.assembleDomainToPublicModelView(communityDO));
    //    }

    //    public ResponseEntity<?> getFullCommunityStreamComponentCO(ObjectId id) {
    //        var communityDO = communityService.findBy_id(id);
    //        //GET PUBLIC VERSION
    //        return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToFullModelView(communityDO));
    //    }


    }
}
