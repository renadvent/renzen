package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ProfileInfoComponentCO;
import com.ren.renzen.CommandObjects.Public.ProfileTabComponentCOPublic;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserViewerController {

    @GetMapping(path = "/getProfiles")
    public ResponseEntity<CollectionModel<?>> getAllProfiles() {
        return ResponseEntity
                .ok(profileStreamCOAssembler.toCollectionModel(userService.findAll()));
    }

    @GetMapping(path = "/getProfileStreamComponentCO/{id}")
    public ProfileInfoComponentCO getProfileStreamComponentCO(@PathVariable ObjectId id) {
        return profileStreamCOAssembler.toModel(userService.findBy_id(id));
    }

    @RequestMapping(path = "/profileTabComponentCO/{id}")
    public ResponseEntity<ProfileTabComponentCOPublic> getProfileTabComponentCO(@PathVariable ObjectId id) {
        return ResponseEntity.ok(profileTabCOAssembler.toModel(userService.findBy_id(id)));
    }
}
