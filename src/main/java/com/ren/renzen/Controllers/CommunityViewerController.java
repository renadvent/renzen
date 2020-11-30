package com.ren.renzen.Controllers;

import com.ren.renzen.ModelAssemblers.CommunityStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.CommunityTabCOAssembler;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Community.GET_COMMUNITY_STREAM_COMPONENT;
import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Community.GET_COMMUNITY_TAB_COMPONENT;

@RestController
public class CommunityViewerController {

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
