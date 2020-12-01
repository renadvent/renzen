package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.google.gson.Gson;
import com.ren.renzen.Converters.*;
import com.ren.renzen.DomainObjects.ArticleDO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Payload.NewCreateArticlePayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.ImageService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import com.ren.renzen.additional.KEYS;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ren.renzen.Controllers.CONTROLLER_PATHS.Article.*;

@RestController
public class ArticleEditorController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;
    final ImageService imageService;

    //converters
    final ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO;
    final ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO;
    final ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO;
    final ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO;
    final CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO;
    final CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO;

    //assemblers
    final ArticleTabCOAssembler articleTabCOAssembler;
    final ProfileStreamCOAssembler profileStreamCOAssembler;
    final ProfileTabCOAssembler profileTabCOAssembler;
    final CommunityTabCOAssembler communityTabCOAssembler;
    final CommunityStreamCOAssembler communityStreamCOAssembler;
    final ArticleStreamCOAssembler articleStreamCOAssembler;
    //ERROR MAP
    final MapValidationErrorService mapValidationErrorService;
    //AZURE
    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;


    public ArticleEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ImageService imageService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
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

    @RequestMapping(PUBLISH_ARTICLE)
    public ResponseEntity<?> publishArticle(@PathVariable ObjectId id, Principal principal) {

        var article = articleService.findBy_id(id);
        var user = userService.findByUsername(principal.getName());

        if (article.getCreatorName().equals(principal.getName())) {

            //TODO add to community

            user.getArticleDraftIDList().remove(id);
            user.getArticleIDList().add(id);
            article.setIsDraft(false);

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
            articleService.deleteArticle(id);
            //TODO remove from user

            //user.getArticleDraftIDList().remove()
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

            userService.update(profile);
            articleService.save(article);

            return ResponseEntity.ok(new Gson().toJson(article.getDislikes()));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @RequestMapping(UPDATE_ARTICLE)
    public ResponseEntity<?> updateArticle(@PathVariable ObjectId id, @RequestBody NewCreateArticlePayload payload, Principal principal) {

        var articleDO = articleService.findBy_id(id);
        var user=userService.findByUsername(principal.getName());

        articleDO.setArticleName(payload.getArticleName());
        articleDO.setTopic(payload.getTopic());
        articleDO.setDescription(payload.getDescription());
        articleDO.setCommunityID(payload.getCommunityID());
        articleDO.setArticleSectionDOList(payload.getArticleSectionDOList());

        user.getWorkNames()
                .stream()
                .filter(name->payload.getWorkName().equals(name))
                .findAny()
                .ifPresentOrElse((value)->{
                },()->user.getWorkNames().add(payload.getWorkName()));
        userService.update(user);

        articleDO.setWorkName(payload.getWorkName());

        ArticleDO savedArticleDO = articleService.save(articleDO);

        return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToFullModelView(savedArticleDO));

    }


    @PostMapping(CREATE_ARTICLE_FROM_SITE)
    public ResponseEntity<?> createArticleFromSite(@RequestBody @Valid NewCreateArticlePayload payload, BindingResult result, Principal principal) {

        //CHECK BINDING RESULTS OF PAYLOAD
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) return errorMap;

        //BUILD PAYLOAD TO ARTICLE
        var articleDO = new ArticleDO();
        articleDO.setArticleName(payload.getArticleName());
        articleDO.setTopic(payload.getTopic());
        articleDO.setDescription(payload.getDescription());
        articleDO.setCommunityID(payload.getCommunityID());
        articleDO.setArticleSectionDOList(payload.getArticleSectionDOList());
        //USES PRINCIPAL LOGIN TO SET
        articleDO.setCreatorName(principal.getName());
        articleDO.setCreatorID(userService.findByUsername(principal.getName()).get_id());

        //GET REFERENCED OBJECTS
        ProfileDO profileDO = userService.findBy_id(articleDO.getCreatorID());
        CommunityDO communityDO = communityService.findBy_id(articleDO.getCommunityID());

        //NEW
        articleDO.setPostImageURL(payload.getImage());
        articleDO.setWorkName(payload.getWorkName());
        //articleDO.

        //save ArticleDO to get an ID from mongodb for it
        ArticleDO savedArticleDO = articleService.save(articleDO);

        //add article to user
        profileDO.getArticleIDList().add(savedArticleDO.get_id());
        userService.update(profileDO);

        //add article to community
        communityDO.getArticleDOList().add(savedArticleDO.get_id());
        communityService.saveOrUpdateCommunity(communityDO, principal.getName());

        return ResponseEntity.ok(articleTabCOAssembler.assembleDomainToFullModelView(savedArticleDO));

    }

    @PostMapping(DELETE_IMAGE_FROM_PROFILE_COMPAT)
    public void deleteImageFromProfile(@PathVariable String link, Principal principal) {

        var user = userService.findByUsername(principal.getName());

        //TODO not working as intended
        var newList = user.getPublicScreenshotsIDList().stream().filter(imageLink -> {
            var name = imageLink.substring(imageLink.lastIndexOf('/') + 1);
            if (name.equals(link)) {
                containerClient.getBlobClient(link).delete();
                return false;
            } else {
                return true;
            }

        }).collect(Collectors.toList());

        user.setPublicScreenshotsIDList(newList);
        userService.update(user);

    }

    @RequestMapping(CREATE_ARTICLE_DRAFT_FROM_APP)
    public Map<String, String> createDraftFromApp(@RequestBody Map<String, Object> payload) {

        String title = "no title";
        ObjectId userId = null;
        File file = null;
        String fileContents = "";

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

        //blobClient.generateUserDelegationSas()

        String url = blobClient.getBlobUrl();

        var user = userService.findBy_id(userId);

        //TODO new to create Article
        var article = new ArticleDO();
        article.setArticleName("DRAFT");
        article.setWorkName("DRAFT");
        article.setCreatorName(user.getUsername());
        article.setCreatorID(user.get_id());
        article.setPostImageURL(url);
        article.setIsDraft(true);
        article = articleService.save(article);

        //add article to drafts
        user.getArticleDraftIDList().add(article.get_id());
        userService.update(user);

        var responseMap = new HashMap<String, String>();
        responseMap.put("articleID", article.get_id().toHexString());
        return responseMap;
    }

}
