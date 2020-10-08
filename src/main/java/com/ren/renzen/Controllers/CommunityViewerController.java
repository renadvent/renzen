package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.CommunityInfoComponentCO;
import com.ren.renzen.ModelAssemblers.CommunityStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.CommunityTabCOAssembler;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    @GetMapping(path = "/getCommunityStreamComponentCO/{id}")
    public ResponseEntity<?> getCommunityStreamComponentCO(@PathVariable ObjectId id, Principal principal) {

        var communityDO = communityService.findBy_id(id);

        if (!userService.findByUsername(principal.getName()).equals(communityDO.getCreatorName())){
            //GET PUBLIC VERSION
            return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToPublicModelView(communityDO));
        }else{
            //GET FULL VERSION
            return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToFullModelView(communityDO));
        }
    }

    //TODO update toModel
    @RequestMapping(path = "/communityTabComponent/{id}")
    public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable("id") ObjectId id, Principal principal) {
        var communityDO = communityService.findBy_id(id);

        if (!userService.findByUsername(principal.getName()).equals(communityDO.getCreatorName())) {
            //GET PUBLIC VERSION
            return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToPublicModelView(communityDO));
        } else {
            //GET FULL VERSION
            return ResponseEntity.ok(communityStreamCOAssembler.assembleDomainToFullModelView(communityDO));
        }
    }
}
