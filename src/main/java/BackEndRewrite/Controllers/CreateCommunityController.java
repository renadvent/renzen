package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.Converters.CommunityDO_to_CommunityTabComponentCO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.ModelAssemblers.CommunityTabCOAssembler;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.DiscussionService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class CreateCommunityController {

    final CommunityService communityService;
    final DiscussionService discussionService;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityTabCOAssembler communityTabCOAssembler;

    public CreateCommunityController(CommunityService communityService, DiscussionService discussionService, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityTabCOAssembler communityTabCOAssembler) {
        this.communityService = communityService;
        this.discussionService = discussionService;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
        this.communityTabCOAssembler = communityTabCOAssembler;
    }


    /**
     * creates a community
     * uses ProfileDO constructor (String name, String creatorID)
     *then converts it to a tabCO and returns it
     *
     * @param communityDO
     * @return
     */
    @PostMapping(
            path="/createCommunity",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<?> createCommunity(@RequestBody CommunityDO communityDO){
//		UserDomainObject found_user = user_repository.findById(createdByUserID).orElseThrow(IllegalStateException::new);
        if (communityService.findDOByName(communityDO.getName())!=null){
            throw new RuntimeException("Community Name already in use");
        }

        //create new discussion for this community
        DiscussionDO discussionDO = new DiscussionDO();
        discussionDO=discussionService.save(discussionDO);

        //add discussion id to community
        communityDO.setDiscussionID(discussionDO.getId());

        communityDO = communityService.save(communityDO);

        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(communityDO);
        EntityModel<CommunityTabComponentCO> entityModel = communityTabCOAssembler.toModel(communityTabComponentCO);

        //now convert DO to CO
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
