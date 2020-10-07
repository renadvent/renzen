package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.CommunityInfoComponentCO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityViewerController {

    //TODO update toModel
    @GetMapping(path = "/getCommunityStreamComponentCO/{id}")
    public CommunityInfoComponentCO getCommunityStreamComponentCO(@PathVariable ObjectId id) {
        return communityStreamCOAssembler.toModel(communityService.findBy_id(id));
    }

    //TODO update toModel
    @RequestMapping(path = "/communityTabComponent/{id}")
    public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable("id") ObjectId id) {
        return ResponseEntity
                .ok(communityTabCOAssembler.toModel(communityService.findBy_id(id)));
    }
}
