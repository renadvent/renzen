package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.DomainObjects.ProfileDO;
import BackEndRewrite.ModelAssemblers.ArticleComponentCOAssembler;
import BackEndRewrite.Services.ArticleServiceImpl;
import BackEndRewrite.Services.DiscussionServiceImpl;
import BackEndRewrite.Services.UserServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//TODO move logic to ArticleService

@RestController
public class CreateArticleController {

    final UserServiceImpl userService;
    final ArticleServiceImpl articleService;
    final DiscussionServiceImpl discussionService;

    //test
    final ArticleComponentCOAssembler assembler;

    public CreateArticleController(UserServiceImpl userService, ArticleServiceImpl articleService, DiscussionServiceImpl discussionService, ArticleComponentCOAssembler assembler) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
        this.assembler = assembler;
    }


    //uselessly used in test assembler
    public Class<?> doNothing() {
        return null;
    }

    //TODO move most of this to service...?
    @PostMapping(path = "/articles/createArticle")
    public ResponseEntity<?> createArticle(@RequestBody CreateArticlePayload payload) {

        //set initial DO attributes
        ArticleDO articleDO = new ArticleDO();
        articleDO.setName(payload.getName());
        articleDO.setDescription(payload.getDescription());
        articleDO.setUserID(payload.getAuthorID());

        //check if user exists, and then
        //add article to user profile


        //try this route instead?
        Optional<ProfileDO> profileDOOptional = userService.findProfileDOById(articleDO.getUserID());

        //instead of this
        try {
            userService.findProfileDOById(articleDO.getUserID())
                    .ifPresentOrElse(profileDO -> {
                        profileDO.getArticleIDList().add(articleDO.getId());
                    }, () -> {
                        throw new IllegalArgumentException("user not found");
                    });
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }


        //TODO create a blank discussion section for the article
        articleDO.setDiscussionID("");

        //TODO distribute article sections


        /**
         * finds CO component by id of the DO
         * assembles it to have the additional links in the assembler
         * responds that the article was created, and returns the ArticleComponentCO // can return something else
         */

        try {
            return articleService.findArticleComponentCOByID(articleDO.getId())
                    .map(
                            articleComponentCO -> {
                                EntityModel<ArticleComponentCO> entityModel = assembler.toModel(articleComponentCO);

                                return ResponseEntity
                                        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                                        .body(entityModel);
                            }).orElseThrow();
        } catch (Exception e) {
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }


        //if failed...
        //return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class CreateArticlePayload {
        String name;
        String description;
        String authorID;

        //TODO add receptor for article sections
    }

}
