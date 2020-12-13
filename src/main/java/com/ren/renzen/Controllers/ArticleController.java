package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.google.gson.Gson;
import com.ren.renzen.Converters.ArticleConverter;
import com.ren.renzen.Converters.CommunityConverter;
import com.ren.renzen.Converters.ProfileConverter;
import com.ren.renzen.ModelAssemblers.ArticleAssembler;
import com.ren.renzen.ModelAssemblers.CommunityAssembler;
import com.ren.renzen.ModelAssemblers.ProfileAssembler;
import com.ren.renzen.ResourceObjects.DomainObjects.ArticleDO;
import com.ren.renzen.ResourceObjects.Payload.UpdateArticlePayload;
import com.ren.renzen.ResourceObjects.Payload.addCommentPayload;
import com.ren.renzen.ResourceObjects.Payload.respondToPollPayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import com.ren.renzen.additional.KEYS;
import lombok.Synchronized;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.ren.renzen.Controllers.Constants.CONTROLLER_PATHS.Article.*;

public class ArticleController {
    @RestController
    public static class ArticleEditorController {

        //services
        final UserService userService;
        final ArticleService articleService;
        final CommunityService communityService;
        final ImageService imageService;

        //converters
        final ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
        final ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
        final ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
        final ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
        final CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
        final CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

        //assemblers
        final ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler;
        final ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler;
        final ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler;
        final CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler;
        final CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler;
        final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;
        //ERROR MAP
        final MapValidationErrorService mapValidationErrorService;
        final ArrayList<String> accessibleArticleFields = new ArrayList<>(List.of("articleName","created_at"));
        //AZURE
        BlobServiceClient blobServiceClient;
        BlobContainerClient containerClient;


        public ArticleEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ImageService imageService, ArticleConverter.ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleConverter.ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileConverter.ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileConverter.ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityConverter.CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityConverter.CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler, ProfileAssembler.ProfileStreamCOAssembler profileStreamCOAssembler, ProfileAssembler.ProfileTabCOAssembler profileTabCOAssembler, CommunityAssembler.CommunityTabCOAssembler communityTabCOAssembler, CommunityAssembler.CommunityStreamCOAssembler communityStreamCOAssembler, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
            this.userService = userService;
            this.articleService = articleService;
            this.communityService = communityService;
            this.imageService = imageService;
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

            // Create a BlobServiceClient object which will be used to create a container client
            String connectStr = KEYS.CONNECTSTR;
            blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
            //Create a unique name for the container
            String containerName = "renzen-test";//CONTAINER_NAME;
            // Create the container and return a container client object
            containerClient = blobServiceClient.getBlobContainerClient(containerName);
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

        @RequestMapping(PUBLISH_ARTICLE)
        public ResponseEntity<?> publishArticle(@PathVariable ObjectId id, Principal principal) {

            var article = articleService.findBy_id(id);
            var user = userService.findByUsername(principal.getName());

            if (article.getCreatorName().equals(principal.getName())) {

                //TODO add to community

                user.getArticleIDList().add(id);
                user.getArticleDraftIDList().remove(id);
                article.setIsDraft(false);

                article.getUpdated_at().add(new Date());

                articleService.save(article);
                userService.update(user);

                //articleService.deleteArticle(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }

        @RequestMapping(UNPUBLISH_ARTICLE)
        public ResponseEntity<?> unpublishArticle(@PathVariable ObjectId id, Principal principal) {
            var article = articleService.findBy_id(id);
            var user = userService.findByUsername(principal.getName());


            if (article.getCreatorName().equals(principal.getName())) {

                //TODO remove from community

                user.getArticleIDList().remove(id);
                user.getArticleDraftIDList().add(id);
                article.setIsDraft(true);

                article.getUpdated_at().add(new Date());

                articleService.save(article);
                userService.update(user);

                //articleService.deleteArticle(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }

        @RequestMapping(DELETE_ARTICLE)
        public ResponseEntity<?> deleteArticle(@PathVariable ObjectId id, Principal principal) {


            //TODO also delete upload
            var article = articleService.findBy_id(id);
            var user = userService.findByUsername(principal.getName());


            if (article.getCreatorName().equals(principal.getName())) {

                user.getArticleDraftIDList().remove(article.get_id());
                user.getArticleIDList().remove(article.get_id());

                if (article.get_id()!=null){
                    var com = communityService.findBy_id(article.get_id());
                    com.getArticleDOList().remove(article.get_id());
                }

                articleService.deleteArticle(id);
                //TODO remove from user

                return ResponseEntity.ok().build();
            }

            return ResponseEntity.badRequest().build();

        }

        @PostMapping(LIKE_ARTICLE)
        public ResponseEntity<?> likeArticle(@PathVariable ObjectId id, Principal principal) {

            var profile = userService.findByUsername(principal.getName());
            var article = articleService.findBy_id(id);

            //TODO check if user has already liked article

            if (article != null) {

                if (profile.getLikedArticles().contains(id)) {
                    return ResponseEntity.ok().build();
                }

                if (profile.getDislikedArticles().contains(id)) {
                    profile.getDislikedArticles().remove(id);
                    article.setDislikes(article.getDislikes() - 1);
                }


                profile.getLikedArticles().add(id);
                article.setLikes(article.getLikes() + 1);
                article.getUpdated_at().add(new Date());


                userService.update(profile);
                articleService.save(article);

                //TODO just return whole page?
                return ResponseEntity.ok(new Gson().toJson(article.getLikes()));
            } else {
                return ResponseEntity.badRequest().build();
            }

        }

        @PostMapping(DISLIKE_ARTICLE)
        public ResponseEntity<?> dislikeArticle(@PathVariable ObjectId id, Principal principal) {

            var profile = userService.findByUsername(principal.getName());
            var article = articleService.findBy_id(id);

            if (article != null) {

                if (profile.getDislikedArticles().contains(id)) {
                    return ResponseEntity.ok().build();
                }

                if (profile.getLikedArticles().contains(id)) {
                    profile.getLikedArticles().remove(id);
                    article.setLikes(article.getLikes() - 1);
                }

                profile.getDislikedArticles().add(id);
                article.setDislikes(article.getDislikes() + 1);
                article.getUpdated_at().add(new Date());


                userService.update(profile);
                articleService.save(article);

                return ResponseEntity.ok(new Gson().toJson(article.getDislikes()));
            } else {
                return ResponseEntity.badRequest().build();
            }

        }

        //TODO work on
        @Synchronized
        @RequestMapping(UPDATE_ARTICLE)
        public ResponseEntity<?> updateArticle(@PathVariable ObjectId id, @RequestBody UpdateArticlePayload payload, Principal principal) {

            try{


            var articleDO = articleService.findBy_id(id);
            var user = userService.findByUsername(principal.getName());

            System.out.println(payload);
            System.out.println(articleDO);

            articleDO.setArticleName(payload.getArticleName());





            //update community lists
            if (articleDO.getCommunityID()!=payload.getCommunityID()){

                if (payload.getCommunityID()!=null){

                    var oldCom = communityService.findBy_id(articleDO.getCommunityID());
                    oldCom.getArticleDOList().remove(articleDO.get_id());

                    var newCom = communityService.findBy_id(payload.getCommunityID());
                    newCom.getArticleDOList().add(articleDO.get_id());

                    communityService.save(oldCom);
                    communityService.save(newCom);
                }

            }

            articleDO.setCommunityID(payload.getCommunityID());

            articleDO.setArticleSectionDOList(payload.getArticleSectionDOList());

            articleDO.getUpdated_at().add(new Date());

            user.getWorkNames()
                    .stream()
                    .filter(name -> payload.getWorkName().equals(name))
                    .findAny()
                    .ifPresentOrElse((value) -> {
                    }, () -> user.getWorkNames().add(payload.getWorkName()));

            userService.update(user);

            articleDO.setWorkName(payload.getWorkName());

            ArticleDO savedArticleDO = articleService.save(articleDO);

                return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToFullModelView(savedArticleDO));

            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }

        }

        //TODO work on
        @Synchronized
        @RequestMapping(CREATE_ARTICLE_DRAFT_FROM_APP)
        public Map<String, String> createDraftFromApp(@RequestBody Map<String, Object> payload) {

            ObjectId userId = null;
            File file = null;

            try {
                userId = new ObjectId(payload.get("userId").toString());
                file = File.createTempFile("image", ".png");
                Files.write(file.toPath(), Base64.getDecoder().decode(payload.get("file").toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            //upload to azure
            // Get a reference to a blob
            BlobClient blobClient = containerClient.getBlobClient(file.getName());
            blobClient.uploadFromFile(file.getAbsolutePath());
            file.delete();

            BlobSasPermission blobPermission = new BlobSasPermission().setReadPermission(true);

            //generate link
            var blobServiceSasSignatureValues = new BlobServiceSasSignatureValues()
                    .setProtocol(SasProtocol.HTTPS_ONLY) // Users MUST use HTTPS (not HTTP).
                    .setExpiryTime(OffsetDateTime.now().plusDays(2))
                    .setPermissions(blobPermission);

            var SAS = blobClient.generateSas(blobServiceSasSignatureValues);

            String url = blobClient.getBlobUrl();

            //--------------------------------------------------------------

            var user = userService.findBy_id(userId);

            //TODO new to create Article
            var article = new ArticleDO();
            article = articleService.save(article);


            article.setCreated_at(new Date());
            article.getUpdated_at().add(new Date());

            article.setArticleName("DRAFT");
            article.setWorkName("DRAFTS");
            article.setCreatorName(user.getUsername());
            article.setCreatorID(user.get_id());
            article.setPostImageURL(url);
            article.setIsDraft(true);

            article.setCommunityID(user.getNoneCommunity());


            var noneCom = communityService.findBy_id(user.getNoneCommunity());
            noneCom.getArticleDOList().add(article.get_id());

            communityService.save(noneCom);


            article = articleService.save(article);

            //add article to drafts
            user.getArticleDraftIDList().add(article.get_id());
            userService.update(user);

            var responseMap = new HashMap<String, String>();
            responseMap.put("articleID", article.get_id().toHexString());
            return responseMap;
        }

        @GetMapping(path = "/updateArticleField")
        public ResponseEntity<?> updateArticleField() {
            return null;
        }

        @Synchronized
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


    }

    @RestController
    public static class ArticleViewerController {

        //SERVICES
        final UserService userService;
        final ArticleService articleService;

        //ASSEMBLERS
        final ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler;
        final ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler;

        public ArticleViewerController(UserService userService, ArticleService articleService, ArticleAssembler.ArticleStreamCOAssembler articleStreamCOAssembler, ArticleAssembler.ArticleTabCOAssembler articleTabCOAssembler) {
            this.userService = userService;
            this.articleService = articleService;
            this.articleStreamCOAssembler = articleStreamCOAssembler;
            this.articleTabCOAssembler = articleTabCOAssembler;
        }

        @GetMapping(GET_ARTICLE_STREAM_COMPONENTCO)
        public ResponseEntity<?> getArticleStreamComponentCO(@PathVariable ObjectId id, Principal principal) {


            var articleDO = articleService.findBy_id(id);

            //        if (!userService.findByUsername(principal.getName()).equals(articleDO.getCreatorName())) {
            if (principal == null || !principal.getName().equals(articleDO.getCreatorName())) {
                //GET PUBLIC VERSION
                return ResponseEntity.ok(articleStreamCOAssembler.assembleDomainToPublicModelView(articleDO));
            } else {
                //GET FULL VERSION
                return ResponseEntity.ok(articleStreamCOAssembler.assembleDomainToFullModelView(articleDO));
            }
        }

        //TODO update toModel
        @GetMapping(GET_ARTICLE_TAB_COMPONENTCO)
        public ResponseEntity<?> getArticleTabComponentCO(@PathVariable ObjectId id, Principal principal) {
            var articleDO = articleService.findBy_id(id);

            //if (!userService.findByUsername(principal.getName()).equals(articleDO.getCreatorName())) {
            if (principal == null || !principal.getName().equals(articleDO.getCreatorName())) {
                //GET PUBLIC VERSION
                return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToPublicModelView(articleDO));
            } else {
                //GET FULL VERSION
                return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToFullModelView(articleDO));
            }
        }
    }
}
