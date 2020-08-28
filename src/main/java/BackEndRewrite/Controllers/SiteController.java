package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.CommunityDO_to_CommunityTabComponentCO;
import BackEndRewrite.Converters.ProfileDO_to_ProfileTabComponentCO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.Repositories.ArticleRepository;
import BackEndRewrite.Repositories.CommunityRepository;
import BackEndRewrite.Repositories.DiscussionRepository;
import BackEndRewrite.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@CrossOrigin("*")
public class SiteController {
    final UserRepository user_repository;
    final CommunityRepository community_repository;
    final ArticleRepository article_repository;
    final DiscussionRepository discussionRepository;

    final ProfileDO_to_ProfileTabComponentCO profileTabConverter;
    final CommunityDO_to_CommunityTabComponentCO communityTabConverter;

    //final CommunityDO_to

    public SiteController(UserRepository user_repository, CommunityRepository community_repository,
                          ArticleRepository article_repository, DiscussionRepository discussionRepository, ProfileDO_to_ProfileTabComponentCO profileTabConverter,
                          CommunityDO_to_CommunityTabComponentCO communityTabConverter) {
        this.user_repository = user_repository;
        this.community_repository = community_repository;
        this.article_repository = article_repository;
        this.discussionRepository = discussionRepository;
        this.profileTabConverter = profileTabConverter;
        this.communityTabConverter = communityTabConverter;
    }



    /**
     * returns the index home page
     * @return
     */
    @RequestMapping()
    public String index(){
        return "index";
    }


//    /**
//     * Used to register a username and password
//     * Returns info to form a user tab component in react
//     *
//     * @param payload
//     * @return
//     */
//    @RequestMapping(path="/register")
//    @ResponseBody
//    public ProfileTabComponentCO Register(@RequestBody SitePayloads.UserNamePassword payload){
//        if (user_repository.findByUsername(payload.username)!=null){
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "username is taken");
//        }
//        ProfileDO user = new ProfileDO(payload.username,payload.password);
//        user_repository.save(user);
//        return profileTabConverter.convert(user);
//    }

//    /**
//     * Used to login to website using username and password
//     * Returns info to form a user tab component in react
//     *
//     * @param payload
//     * @return
//     */
//    @RequestMapping(path="/login")
//    @ResponseBody
//    public ProfileTabComponentCO Login(@RequestBody SitePayloads.UserNamePassword payload){
//        ProfileDO profile = user_repository.findByUsername(payload.username);
//        if (profile!=null){
//            if (payload.password.equals(profile.getPassword())){
//                return (profileTabConverter.convert(profile));
//            }
//        }
//        return null;
//    }

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
