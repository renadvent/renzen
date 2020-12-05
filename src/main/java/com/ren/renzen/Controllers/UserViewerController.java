package com.ren.renzen.Controllers;

import com.ren.renzen.CommandObjects.ProfileDTOs;
import com.ren.renzen.ModelAssemblers.ProfileStreamCOAssembler;
import com.ren.renzen.ModelAssemblers.ProfileTabCOAssembler;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.User.GET_PROFILE_STREAM_COMPONENT;
import static com.ren.renzen.Controllers.CONTROLLER_PATHS.User.GET_PROFILE_TAB_COMPONENT;

@RestController
public class UserViewerController {

    final UserService userService;

    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;

    public UserViewerController(UserService userService, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler) {
        this.userService = userService;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.profileTabCOAssembler = profileTabCOAssembler;
    }

    @GetMapping(GET_PROFILE_STREAM_COMPONENT)
    public ResponseEntity<ProfileDTOs.ProfileInfoComponentCO> getProfileStreamComponentCO(@PathVariable ObjectId id, Principal principal) {

        var profileDO = userService.findBy_id(id);

        if (principal == null || !principal.getName().equals(profileDO.getUsername())) {
            //GET PUBLIC VERSION
            return ResponseEntity.ok(profileStreamCOAssembler.assembleDomainToPublicModelView(profileDO));
        } else {
            //GET FULL VERSION
            return ResponseEntity.ok(profileStreamCOAssembler.assembleDomainToFullModelView(profileDO));
        }
    }

    @RequestMapping(GET_PROFILE_TAB_COMPONENT)
    public ResponseEntity<ProfileDTOs.ProfileTabComponentCO> getProfileTabComponentCO(@PathVariable ObjectId id, Principal principal) {
        var profileDO = userService.findBy_id(id);

        if (principal == null || !principal.getName().equals(profileDO.getUsername())) {
            //GET PUBLIC VERSION
            return ResponseEntity.ok(profileTabCOAssembler.assembleDomainToPublicModelView(profileDO));
        } else {
            //GET FULL VERSION
            return ResponseEntity.ok(profileTabCOAssembler.assembleDomainToFullModelView(profileDO));
        }
    }
}
