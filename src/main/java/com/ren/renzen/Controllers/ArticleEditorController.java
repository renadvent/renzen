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
import com.ren.renzen.DomainObjects.ArticleSectionDO;
import com.ren.renzen.DomainObjects.CommunityDO;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.Exceptions.OwnerMismatchException;
import com.ren.renzen.ModelAssemblers.*;
import com.ren.renzen.Payload.NewCreateArticlePayload;
import com.ren.renzen.Services.Interfaces.ArticleService;
import com.ren.renzen.Services.Interfaces.CommunityService;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.Services.MapValidationErrorService;
import com.ren.renzen.additional.KEYS;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
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

import static com.ren.renzen.additional.KEYS.CONTAINER_NAME;

@RestController
public class ArticleEditorController {

    //services
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

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

    public ArticleEditorController(UserService userService, ArticleService articleService, CommunityService communityService, ArticleDO_to_ArticleTabComponentCO articleDO_to_articleTabComponentCO, ArticleDO_to_ArticleStreamComponentCO articleDO_to_articleStreamComponentCO, ProfileDO_to_ProfileTabComponentCO profileDO_to_profileTabComponentCO, ProfileDO_to_ProfileStreamComponentCO profileDO_to_profileStreamComponentCO, CommunityDO_to_CommunityTabComponentCO communityDO_to_communityTabComponentCO, CommunityDO_to_CommunityStreamComponentCO communityDO_to_communityStreamComponentCO, ArticleTabCOAssembler articleTabCOAssembler, ProfileStreamCOAssembler profileStreamCOAssembler, ProfileTabCOAssembler profileTabCOAssembler, CommunityTabCOAssembler communityTabCOAssembler, CommunityStreamCOAssembler communityStreamCOAssembler, ArticleStreamCOAssembler articleStreamCOAssembler, MapValidationErrorService mapValidationErrorService) {
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


        // Create a BlobServiceClient object which will be used to create a container client
        String connectStr = KEYS.CONNECTSTR;
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        //Create a unique name for the container
        String containerName = CONTAINER_NAME;
        // Create the container and return a container client object
        containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    @RequestMapping(path="/publishArticle/{id}")
    public ResponseEntity<?> publishArticle(@PathVariable ObjectId id, Principal principal){
        var article = articleService.findBy_id(id);
        if (article.getCreatorName().equals(principal.getName())){

            article.setIsDraft(false);
            articleService.save(article);
            //articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(path="/unpublishArticle/{id}")
    public ResponseEntity<?> unpublishArticle(@PathVariable ObjectId id, Principal principal){
        var article = articleService.findBy_id(id);
        if (article.getCreatorName().equals(principal.getName())){

            article.setIsDraft(true);
            articleService.save(article);
            //articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @RequestMapping(path="/deleteArticle/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable ObjectId id, Principal principal){


        //TODO also delete upload
        var article = articleService.findBy_id(id);

        if (article.getCreatorName().equals(principal.getName())){
            articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping(path = "/likeArticle/{id}")
    public ResponseEntity<?> likeArticle(@PathVariable ObjectId id,Principal principal){

        var profile = userService.findByUsername(principal.getName());
        var article = articleService.findBy_id(id);

        //TODO check if user has already liked article

        if (article!=null){

            if (profile.getLikedArticles().contains(id)){
                return ResponseEntity.ok().build();
            }

            if (profile.getDislikedArticles().contains(id)){
                profile.getDislikedArticles().remove(id);
                article.setDislikes(article.getDislikes()-1);
            }


            profile.getLikedArticles().add(id);
            article.setLikes(article.getLikes()+1);

            userService.update(profile);
            articleService.save(article);

            //TODO just return whole page?
            return ResponseEntity.ok(new Gson().toJson(article.getLikes()));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping(path = "/dislikeArticle/{id}")
    public ResponseEntity<?> dislikeArticle(@PathVariable ObjectId id,Principal principal){

        var profile = userService.findByUsername(principal.getName());
        var article = articleService.findBy_id(id);

        if (article!=null){

            if (profile.getDislikedArticles().contains(id)){
                return ResponseEntity.ok().build();
            }

            if (profile.getLikedArticles().contains(id)){
                profile.getLikedArticles().remove(id);
                article.setLikes(article.getLikes()-1);
            }

            profile.getDislikedArticles().add(id);
            article.setDislikes(article.getDislikes()+1);

            userService.update(profile);
            articleService.save(article);

            return ResponseEntity.ok(new Gson().toJson(article.getDislikes()));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }


    @PostMapping(path = "/createArticle")
    public ResponseEntity<?> createArticle(@RequestBody @Valid NewCreateArticlePayload payload, BindingResult result, Principal principal) {

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
        //return ResponseEntity.ok(articleComponentCOAssembler.toModel(articleService.findBy_id(savedArticleDO.get_id())));

    }



    @PostMapping(path = "/addScreenshotToArticle/{id}")
    public String addScreenshotToArticle(@PathVariable ObjectId id, @RequestBody String screenshot, Principal principal) {

        //CHECK IF LOGGED ON USER IS THE OWNDER OF THE ARTICLE
//        if (!userService.findByUsername(principal.getName()).equals(articleService.findBy_id(id).getCreatorName())) {
        if (!principal.getName().equals(articleService.findBy_id(id).getCreatorName())) {
            throw new OwnerMismatchException("Logged In User Does Not Own Article");
        }

        //UPLOAD IMAGE TO AZURE

        File file = null;

        try {
            file = File.createTempFile("image", ".png");
            Files.write(file.toPath(), Base64.getDecoder().decode(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }

        BlobClient blobClient = containerClient.getBlobClient(file.getName());
        blobClient.uploadFromFile(file.getAbsolutePath());
        file.delete();

        //ADD IMAGE TO ARTICLE

        var article = articleService.findBy_id(id);

        article.getArticleSectionDOList().add(new ArticleSectionDO(blobClient.getBlobUrl()));
        articleService.save(article);

        return blobClient.getBlobUrl();
    }




    //--------------------------------------------------------------


    @PostMapping(path="/deleteImageFromProfile/{link}")
    public void deleteImageFromProfile(@PathVariable String link, Principal principal){

        var user = userService.findByUsername(principal.getName());

        //TODO not working as intended
        var newList = user.getPublicScreenshotsIDList().stream().filter(imageLink->{
            var name = imageLink.substring(imageLink.lastIndexOf('/') + 1);
            if (name.equals(link)){
                containerClient.getBlobClient(link).delete();
                return false;
            }else{
                return true;
            }

        }).collect(Collectors.toList());

        user.setPublicScreenshotsIDList(newList);
        userService.save(user);


//        BlobClient blobClient = containerClient.getBlobClient(link);
//        blobClient.delete();
    }

    //TODO changing to return article
    /**
     * returns link to image that was saved
     *
     * @param payload
     * @return
     */
    @PostMapping(path = "/addImage", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String addImageToProfile(@RequestBody Map<String, Object> payload) {
    public Map<String,String> addImageToProfile(@RequestBody Map<String, Object> payload) {
        String title = "no title";
        ObjectId userId = null;
        File file = null;
        String fileContents = "";

        //TODO this can be better
        //get expected data from map
        for (Map.Entry<String, Object> me : payload.entrySet()) {

            if (me.getKey().equals("userId")) {
                userId = new ObjectId(me.getValue().toString());
//                userId=new ObjectId(me.getValue().toString());
                var user = userService.findBy_id(userId);
            }

            if (me.getKey().equals("title")) {
                title = (String) me.getValue();
            }

            if (me.getKey().equals("file")) {
                try {
                    //fileContents = me.getValue().toString();
                    //
                    file = File.createTempFile("image", ".png");
                    Files.write(file.toPath(), Base64.getDecoder().decode(me.getValue().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
        user.getPublicScreenshotsIDList().add(url); // adds url without sas. wiil have to be generated when retrieving
        userService.update(user);

        //TODO new to create Article
        var article = new ArticleDO();
        article.setArticleName("DRAFT");
        article.setWorkName("NONE");
        article.setCreatorName(user.getUsername());
        article.setCreatorID(user.get_id());
        article.setPostImageURL(url);

        //NEW
        article.setIsDraft(true);

        articleService.save(article);
        //article.setC

        var urlWithPermissions = url + "?" + SAS;


        var responseMap = new HashMap<String, String>();
        responseMap.put("absoluteURL",url);
        responseMap.put("SASUrl",urlWithPermissions);

        //TODO will have to create a way for web client to read this into draft page
        responseMap.put("ARTICLEID",article.get_id().toHexString());
        return responseMap;


        //return urlWithPermissions; // link with sas
    }


}
