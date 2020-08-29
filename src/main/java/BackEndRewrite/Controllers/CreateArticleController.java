package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO move logic to ArticleService

@RestController
public class CreateArticleController {

    final UserService userService;
    final ArticleService articleService;
    final DiscussionService discussionService;
    final CommunityService communityService;

    //test
    final ArticleComponentCOAssembler assembler;

    public CreateArticleController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ArticleComponentCOAssembler assembler) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
        this.communityService = communityService;
        this.assembler = assembler;
    }


    //uselessly used in test assembler
    public Class<?> doNothing() {
        return null;
    }

    //TODO move most of this to service...?
    @PostMapping(path = "/articles/createArticle")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticlePayload payload) {

        //check if provided ids exist
        ProfileDO profileDO = userService.findProfileDOById(payload.authorID);
        CommunityDO communityDO = communityService.findCommunityDOById(payload.communityID);


        //save ArticleDO to get an ID from mongodb for it
        ArticleDO savedArticleDO = articleService.save(new ArticleDO(payload.getName(), payload.getDescription(),
                payload.getAuthorID(), payload.getCommunityID(), payload.getArticleSectionDOList()));

        //add article to user
        profileDO.getArticleIDList().add(savedArticleDO.getId());

        //add article to community
        communityDO.getArticleDOList().add(savedArticleDO.getId());

        /**
         * finds CO component by id of the DO
         * assembles it to have the additional links in the assembler
         * responds that the article was created, and returns the ArticleComponentCO // can return something else
         */
        ArticleComponentCO articleDOOptional = articleService.findArticleComponentCOByID(savedArticleDO.getId());

        EntityModel<ArticleComponentCO> entityModel = assembler.toModel(articleDOOptional);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class CreateArticlePayload {
        String name;
        String description;
        String authorID;
        String communityID;

        List<ArticleSectionDO> articleSectionDOList;

        //TODO add receptor for article sections
    }

}
