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

import java.util.Optional;
import java.util.Set;

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
        Optional<ProfileDO> profileDOOptional = userService.findProfileDOById(payload.authorID);
        Optional<CommunityDO> communityDOOptional = communityService.findCommunityDOById(payload.communityID);

        if (profileDOOptional.isEmpty() ||  communityDOOptional.isEmpty()){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }

        //save ArticleDO to get an ID from mongodb for it
        ArticleDO savedArticleDO = articleService.save(new ArticleDO(payload.getName(),payload.getDescription(),
                payload.getAuthorID(),payload.getCommunityID()));

        //add article to user
        profileDOOptional.get().getArticleIDList().add(savedArticleDO.getId());

        //add article to community
        communityDOOptional.get().getArticleDOList().add(savedArticleDO.getId());

        //TODO distribute article sections

        //TODO create a blank discussion section for the article


        /**
         * finds CO component by id of the DO
         * assembles it to have the additional links in the assembler
         * responds that the article was created, and returns the ArticleComponentCO // can return something else
         */
        Optional<ArticleComponentCO> articleDOOptional = articleService.findArticleComponentCOByID(savedArticleDO.getId());

        if (articleDOOptional.isEmpty()){
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }else{
            EntityModel<ArticleComponentCO> entityModel = assembler.toModel(articleDOOptional.get());
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class CreateArticlePayload {
        String name;
        String description;
        String authorID;
        String communityID;

        Set<ArticleSectionDO> articleSectionDOSet;

        //TODO add receptor for article sections
    }

}
