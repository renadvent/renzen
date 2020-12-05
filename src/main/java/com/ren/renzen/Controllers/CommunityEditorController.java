package com.ren.renzen.Controllers;

import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Payload.CreateCommunityPayload;
import com.ren.renzen.Payload.JoinCommunityPayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Community.CREATE_COMMUNITY;
import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Community.JOIN_COMMUNITY;

@RestController
public class CommunityEditorController {


    //services
    final UserService userService;
    final ArticleService articleService;
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

    //
    final MapValidationErrorService mapValidationErrorService;

    public CommunityEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
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

        profileDO.getCommunityIDList().add(communityDO.get_id());
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
        profileDO.getCommunityIDList().add(communityDO.get_id());
        userService.update(profileDO);

        return ResponseEntity.ok(communityTabCOAssembler.assembleDomainToFullModelView(communityDO));
    }


}
