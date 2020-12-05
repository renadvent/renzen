package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.ren.renzen.Converters.*;
import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.ResourceObjects.Payload.addCommentPayload;
import com.ren.renzen.ResourceObjects.Payload.respondToPollPayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.*;

@Slf4j
@RestController
public class FeedController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    //converters
    final ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;
    //ERROR MAP
    final MapValidationErrorService mapValidationErrorService;
    /**
     * uses to get likes or comments?
     * will need to add security checks
     *
     * @param id
     * @param field
     * @param principal
     * @return
     */

    final ArrayList<String> accessibleArticleFields = new ArrayList<>(List.of("articleName"));
    //AZURE
    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;

    //add comment
    //respondToPoll
    //getExploreFeed
    //getYourFeed
    //refreshComments


    //TODO work on
    public FeedController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
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

    @GetMapping(path = "/updateArticleField")
    public ResponseEntity<?> updateArticleField() {
        return null;
    }

    @GetMapping(path = "/getArticleField/{id}/{field}")
    public ResponseEntity<?> getArticleField(@PathVariable ObjectId id, @PathVariable String field, Principal principal) {


        if (!accessibleArticleFields.contains(field)) return ResponseEntity.badRequest().build();


        try {

            var article = articleService.findBy_id(id);
            var foundValue = article.getClass().getDeclaredField(field);
            foundValue.setAccessible(true);

            return ResponseEntity.ok(foundValue.get(article).toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping(ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable ObjectId id, @RequestBody addCommentPayload payload, Principal principal) {

        System.out.println(payload);

        var article = articleService.findBy_id(id);

        var comment = new ArticleDO.Comment();
        comment.setComment(payload.getComment());
        comment.setAuthor(userService.findByUsername(principal.getName()).get_id());
        comment.setAuthorName(principal.getName());
        article.getComments().add(comment);
        articleService.save(article);

        return ResponseEntity.ok(null);
    }

    @PostMapping(RESPOND_TO_POLL)
    public ResponseEntity<?> respondToPoll(@PathVariable ObjectId id, respondToPollPayload payload, Principal principal) {

        var article = articleService.findBy_id(id);

        article.getPollOptions().forEach(option -> {
            if (option.getName().equals(payload.getOption())) {
                option.setVotes(option.getVotes() + 1);
            }
        });

        return ResponseEntity.ok(null);
    }

    @GetMapping(GET_EXPLORE_FEED)
    public ResponseEntity<?> getExploreFeed(@PathVariable ObjectId id, Principal principal) {
        return null;
    }

    @GetMapping(GET_YOUR_FEED)
    public ResponseEntity<?> getYourFeed(@PathVariable ObjectId id, Principal principal) {
        return null;
    }

    @GetMapping(REFRESH_COMMENTS)
    public ResponseEntity<?> refreshComments(@PathVariable ObjectId id, Principal principal) {
        var article = articleService.findBy_id(id);
        return ResponseEntity.ok(article.getComments());
    }


}
