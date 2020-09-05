package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.StreamComponentCOs.ArticleStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.CommunityStreamComponentCO;
import BackEndRewrite.CommandObjects.StreamComponentCOs.ProfileStreamComponentCO;
import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.CommunityTabComponentCO;
import BackEndRewrite.CommandObjects.TabComponentCOs.ProfileTabComponentCO;
import BackEndRewrite.Converters.*;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.DiscussionDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import BackEndRewrite.ModelAssemblers.ArticleComponentCOAssembler;
import BackEndRewrite.ModelAssemblers.CommunityTabCOAssembler;
import BackEndRewrite.ModelAssemblers.ProfileStreamCOAssembler;
import BackEndRewrite.ModelAssemblers.ProfileTabCOAssembler;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.DiscussionService;
import BackEndRewrite.Services.Interfaces.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin("*")
public class IndexController {

    final UserService userService;
    final ArticleService articleService;
    final DiscussionService discussionService;
    final CommunityService communityService;

    //converters
    final ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;


    //assemblers
    final ArticleComponentCOAssembler articleComponentCOAssembler;

    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityTabCOAssembler communityTabCOAssembler;

    public IndexController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleComponentCOAssembler articleComponentCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
        this.communityService = communityService;
        this.articleDO_to_articleComponentCO = articleDO_to_articleComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
        this.profileDO_to_profileTabComponentCO = profileDO_to_profileTabComponentCO;
        this.profileDO_to_profileStreamComponentCO = profileDO_to_profileStreamComponentCO;
        this.communityDO_to_communityTabComponentCO = communityDO_to_communityTabComponentCO;
        this.communityDO_to_communityStreamComponentCO = communityDO_to_communityStreamComponentCO;
        this.articleComponentCOAssembler = articleComponentCOAssembler;
        this.profileStreamCOAssembler = profileStreamCOAssembler;
        this.profileTabCOAssembler = profileTabCOAssembler;
        this.communityTabCOAssembler = communityTabCOAssembler;
    }


    /**
     * returns the index home page
     * @return
     */
    @RequestMapping()

    public String index(){
        return "index";
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
        if (communityService.checkIfCommunityNameUsed(communityDO.getName())){
            throw new RuntimeException("Community Name already in use");
        }

        //create new discussion for this community
        DiscussionDO discussionDO=discussionService.save(new DiscussionDO());

        //add discussion id to community
        communityDO.setDiscussionID(discussionDO.get_id());

        //save community
        communityDO = communityService.save(communityDO);

        //get tab version of community
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(communityDO);

        //convert co to model
        EntityModel<CommunityTabComponentCO> entityModel = communityTabCOAssembler.toModel(communityTabComponentCO);

        //now convert DO to CO

        return ResponseEntity.ok(entityModel);
//        return ResponseEntity
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//                .body(entityModel);
    }

    @PostMapping(path = "/createArticle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createArticle(@RequestBody CreateArticlePayload payload) {

        //check if provided ids exist
        ProfileDO profileDO = userService.findBy_id(payload.authorID);

        CommunityDO communityDO = communityService.findBy_id(payload.communityID);

        //save ArticleDO to get an ID from mongodb for it
        ArticleDO savedArticleDO = articleService.save(new ArticleDO(payload.getName(), payload.getDescription(),
                payload.getAuthorID(), payload.getCommunityID(), payload.getArticleSectionDOList()));

        //add article to user
        profileDO.getArticleIDList().add(savedArticleDO.get_id());
        userService.save(profileDO);

        //add article to community
        communityDO.getArticleDOList().add(savedArticleDO.get_id());
        communityService.save(communityDO);

        //gets ComponentCO version of article
        ArticleComponentCO articleDO =
                articleDO_to_articleComponentCO.convert(
                        articleService.findBy_id(savedArticleDO.get_id()));

        //creates a model with rest links
        EntityModel<ArticleComponentCO> entityModel = articleComponentCOAssembler.toModel(articleDO);

        //responds that it was created successfully

        return ResponseEntity.ok(entityModel);

//        return ResponseEntity
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//                .body(entityModel);
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class CreateArticlePayload {
        String name;
        String description;
        ObjectId authorID;
        ObjectId communityID;
        String topic;

        List<ArticleSectionDO> articleSectionDOList=new ArrayList<>();

        public CreateArticlePayload(String name,String description,ObjectId authorID,ObjectId communityID,
                                    String topic){
            this.name=name;
            this.description=description;
            this.authorID=authorID;
            this.communityID=communityID;
            this.topic=topic;
        }

        public CreateArticlePayload(String name,String description,ObjectId authorID,ObjectId communityID
                ,List<ArticleSectionDO> articleSectionDOList){
            this.name=name;
            this.description=description;
            this.authorID=authorID;
            this.communityID=communityID;
            this.articleSectionDOList=articleSectionDOList;
        }

    }

    //TODO create discussion
    @PostMapping(path="/createDiscussion")
    public ResponseEntity<?> createDiscussion(@RequestBody createDiscussionPayload payload){
        return null;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    class createDiscussionPayload{

    }

    public class CreateDiscussionSectionController {
    }


    /**
     * Used to login to website using username and password
     * Returns info to form a user tab component in react
     *
     * @param payload
     * @return
     */
    @RequestMapping(path="/login")
    @ResponseBody
    public ProfileTabComponentCO Login(@RequestBody SitePayloads.UserNamePassword payload){
        return profileDO_to_profileTabComponentCO
                .convert(userService.findProfileDOByNameAndPassword(payload.username, payload.password));
    }

    @RequestMapping(path="/register")
    @ResponseBody
    public ResponseEntity<?> Register(@RequestBody SitePayloads.UserNamePassword payload){

        if (userService.checkIfUsernameTaken(payload.username)){
            throw new RuntimeException("user name taken");
        }else{

            //create and save profile
            ProfileDO profileDO = userService.save(new ProfileDO(payload.username,payload.password));

            //get tab component
            ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convert(profileDO);

            //assemble
            EntityModel<ProfileTabComponentCO> entityModel = profileTabCOAssembler.toModel(profileTabComponentCO);

            return ResponseEntity
                    .ok(entityModel);


//            return ResponseEntity
//                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
//                    .body(entityModel);

        }
    }

    @GetMapping(path="/getHomeStreams")
    public ResponseEntity<CollectionModel> getHomeStreams(){


        ArrayList<CollectionModel> returnList = new ArrayList();

        //TODO convert
        returnList.add(getAllArticles().getBody());
        returnList.add(profileStreamCOAssembler.toCollectionModel(userService.findAll()));
        returnList.add(getAllCommunities().getBody());


        return ResponseEntity.ok(CollectionModel.of(returnList));
    }

    //Converter
    public List<?> ResponseEntity_to_List(ResponseEntity<CollectionModel<?>> responseEntity){
        return Arrays.asList(responseEntity.getBody().getContent().toArray());
    }


    @GetMapping(path="/getAllByCommunityIDAndTopic")
    public List<ArticleStreamComponentCO> getAllByCommunityIDAndTopic(@RequestBody getAllByCommunityIDAndTopicPayload payload){

        List<ArticleStreamComponentCO> returnList = new ArrayList<>();

        for (ArticleDO articleDO : articleService.findAllByCommunityIDAndTopic(payload.communityID,payload.topic)){
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }

        return returnList;
    }

    @Getter@Setter
    class getAllByCommunityIDAndTopicPayload{
        ObjectId communityID;
        String topic;
        public getAllByCommunityIDAndTopicPayload(ObjectId communityID,String topic){
            this.communityID=communityID;
            this.topic=topic;
        }
    }

    @GetMapping(path="/getProfiles")
    public ResponseEntity<CollectionModel<?>> getAllProfiles(){
        return ResponseEntity
                .ok(profileStreamCOAssembler.toCollectionModel(userService.findAll()));
    }

    @GetMapping(path="/getCommunitiesByProfile/{id}")
    public List<CommunityStreamComponentCO> getCommunitiesByProfile(@PathVariable ObjectId id){

        List<CommunityStreamComponentCO> returnList = new ArrayList<>();

        for (CommunityDO communityDO : communityService.findByCreatorID(id)){
            returnList.add(communityDO_to_communityStreamComponentCO.convert(communityDO));
        }
        return returnList;
    }

    @GetMapping("/getArticles")
    public ResponseEntity<CollectionModel> getAllArticles(){
        List<ArticleStreamComponentCO> returnList = new ArrayList<>();
        for (ArticleDO articleDO : articleService.findAll()){
            returnList.add(articleDO_to_articleStreamComponentCO.convert(articleDO));
        }
        return ResponseEntity.ok(CollectionModel.of(returnList));
    }

    @GetMapping("/getCommunities")
    public ResponseEntity<CollectionModel> getAllCommunities(){
        List<CommunityStreamComponentCO> returnList = new ArrayList<>();
        for (CommunityDO communityDO : communityService.findAll()){
            returnList.add(communityDO_to_communityStreamComponentCO.convert(communityDO));
        }
        return ResponseEntity.ok(CollectionModel.of(returnList));
    }

    /**
     * returns a profile stream component for react render
     * @param id
     * @return
     */
    @GetMapping(path="/getProfileStreamComponentCO/{id}")
    public ProfileStreamComponentCO getProfileStreamComponentCO(@PathVariable ObjectId id){
        return profileDO_to_profileStreamComponentCO.convert(userService.findBy_id(id));
    }

    /**
     * returns an article stream component for react render
     * @param id
     * @return
     */
    @GetMapping(path="/getArticleStreamComponentCO/{id}")
    public ArticleStreamComponentCO getArticleStreamComponentCO(@PathVariable ObjectId id){
        return articleDO_to_articleStreamComponentCO.convert(articleService.findBy_id(id));
    }

    /**
     * returns a community stream component for react render
     * @param id
     * @return
     */
    @GetMapping(path="/getCommunityStreamComponentCO/{id}")
    public CommunityStreamComponentCO getCommunityStreamComponentCO(@PathVariable ObjectId id){
        return communityDO_to_communityStreamComponentCO.convert(communityService.findBy_id(id));
    }

    /**
     * Get Profile Tab Component CO for React
     * @param id
     * @return
     */
    @RequestMapping(path="/profileTabComponentCO/{id}")
    public ResponseEntity<?> getProfileTabComponentCO(@PathVariable ObjectId id){

        //get Command Object
        ProfileTabComponentCO profileTabComponentCO = profileDO_to_profileTabComponentCO.convert(userService.findBy_id(id));

        //convert Command Object to model
        EntityModel<ProfileTabComponentCO> entityModel = profileTabCOAssembler.toModel(profileTabComponentCO);

        return ResponseEntity.ok(entityModel);
    }

    /**
     * Get Community Tab Component CO for React
     * @param id
     * @return
     */
    @RequestMapping(path="/communityTabComponent/{id}")
    @ResponseBody
    public ResponseEntity<?> getCommunityTabComponentCO(@PathVariable("id") ObjectId id){

        //get command object
        CommunityTabComponentCO communityTabComponentCO = communityDO_to_communityTabComponentCO.convert(communityService.findBy_id(id));

        //TODO convert command object to model
        EntityModel<CommunityTabComponentCO> entityModel = communityTabCOAssembler.toModel(communityTabComponentCO);

        return ResponseEntity
                .ok(communityTabComponentCO);
    }


}
