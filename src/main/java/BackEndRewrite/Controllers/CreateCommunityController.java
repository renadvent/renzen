package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.Converters.ArticleDO_to_ArticleComponentCO;
import BackEndRewrite.Converters.ArticleDO_to_ArticleStreamComponentCO;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCommunityController {

    //services
    final CommunityService communityService;
    final DiscussionService discussionService;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityTabCOAssembler communityTabCOAssembler;

    //converters
    final ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    public CreateCommunityController(CommunityService communityService, DiscussionService discussionService, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityTabCOAssembler communityTabCOAssembler, ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        this.communityService = communityService;
        this.discussionService = discussionService;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
        this.communityTabCOAssembler = communityTabCOAssembler;
        this.articleDO_to_articleComponentCO = articleDO_to_articleComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
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

        //check if community name already exists
        if (communityService.findDOByName(communityDO.getName())!=null){
            throw new RuntimeException("Community Name already in use");
        }

        //create new discussion for this community
        DiscussionDO discussionDO=discussionService.save(new DiscussionDO());

        //add discussion id to community
        communityDO.setDiscussionID(discussionDO.getId());

        //save community
        communityDO = communityService.save(communityDO);

        //get tab version of community
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(communityDO);

        //convert co to model
        EntityModel<CommunityTabComponentCO> entityModel = communityTabCOAssembler.toModel(communityTabComponentCO);

        //now convert DO to CO
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
