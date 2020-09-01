package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.Converters.ArticleDO_to_ArticleComponentCO;
import BackEndRewrite.Converters.ArticleDO_to_ArticleStreamComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.CommunityDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.DomainObjects.Subsections.ArticleSectionDO;
import BackEndRewrite.ModelAssemblers.ArticleComponentCOAssembler;
import BackEndRewrite.Services.Interfaces.ArticleService;
import BackEndRewrite.Services.Interfaces.CommunityService;
import BackEndRewrite.Services.Interfaces.DiscussionService;
import BackEndRewrite.Services.Interfaces.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * creates article
 */
@RestController
public class CreateArticleController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final DiscussionService discussionService;
    final CommunityService communityService;

    //converters
    final ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;

    //assemblers
    final ArticleComponentCOAssembler articleComponentCOAssembler;

    //constructor
    public CreateArticleController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleComponentCOAssembler articleComponentCOAssembler, ArticleDO_to_ArticleComponentCO articleDO_to_articleComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
        this.communityService = communityService;
        this.articleComponentCOAssembler = articleComponentCOAssembler;
        this.articleDO_to_articleComponentCO = articleDO_to_articleComponentCO;
        this.articleDO_to_articleStreamComponentCO = articleDO_to_articleStreamComponentCO;
    }

    @PostMapping(path = "/createArticle",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createArticle(@RequestBody CreateArticlePayload payload) {

        System.out.println("---------------ENTER CREATING ARTICLE");

        //check if provided ids exist
        ProfileDO profileDO = userService.findProfileDOById(payload.authorID);
        System.out.println("---------------FOUND AUTHOR");

        CommunityDO communityDO = communityService.findCommunityDOById(payload.communityID);
        System.out.println("---------------FOUND COMMUNITY");

        //save ArticleDO to get an ID from mongodb for it
        ArticleDO savedArticleDO = articleService.save(new ArticleDO(payload.getName(), payload.getDescription(),
                payload.getAuthorID(), payload.getCommunityID(), payload.getArticleSectionDOList()));

        //add article to user
        profileDO.getArticleIDList().add(savedArticleDO.getId());

        //add article to community
        communityDO.getArticleDOList().add(savedArticleDO.getId());

        //gets ComponentCO version of article
        ArticleComponentCO articleDO =
                articleDO_to_articleComponentCO.convert(
                articleService.findArticleDOByID(savedArticleDO.getId()));

        //creates a model with rest links
        EntityModel<ArticleComponentCO> entityModel = articleComponentCOAssembler.toModel(articleDO);

        //responds that it was created successfully
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class CreateArticlePayload {
        String name;
        String description;
        ObjectId authorID;
        ObjectId communityID;

        List<ArticleSectionDO> articleSectionDOList=new ArrayList<>();

        public CreateArticlePayload(String name,String description,ObjectId authorID,ObjectId communityID
                ){
            this.name=name;
            this.description=description;
            this.authorID=authorID;
            this.communityID=communityID;

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

//    //uselessly used in test assembler
//    @NoArgsConstructor
//    @Getter
//    @Setter
//    public Class<?> doNothing() {
//        return null;
//    }

}
