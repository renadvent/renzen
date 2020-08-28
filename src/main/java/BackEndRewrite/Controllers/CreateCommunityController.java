package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public class CreateCommunityController {

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
    public CommunityTabComponentCO createCommunity(@RequestBody CommunityDO communityDO){
//		UserDomainObject found_user = user_repository.findById(createdByUserID).orElseThrow(IllegalStateException::new);
        if (community_repository.findByName(communityDO.getName())!=null){
            return null;
        }

        //create new discussion for this community
        DiscussionDO discussionDO = new DiscussionDO();
        discussionDO=discussionRepository.save(discussionDO);

        //add discussion id to community
        communityDO.setDiscussionID(discussionDO.getId());

        communityDO = community_repository.save(communityDO);

        //now convert DO to CO
        return communityTabConverter.convert(communityDO);
    }
}
