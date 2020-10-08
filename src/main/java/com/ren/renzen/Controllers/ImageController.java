package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.ren.renzen.Services.Interfaces.*;
import com.ren.renzen.additional.KEYS;
import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Map;

@RestController
public class ImageController {
    final UserService userService;
    final ArticleService articleService;
    final CommunityService communityService;

    final ImageService imageService;

    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;

    public ImageController(UserService userService, ArticleService articleService, CommunityService communityService, ImageService imageService) {
        this.userService = userService;
        this.articleService = articleService;
        this.communityService = communityService;
        this.imageService = imageService;

        // Create a BlobServiceClient object which will be used to create a container client
        String connectStr= KEYS.CONNECTSTR;
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();
        //Create a unique name for the container
        String containerName = "renzen-test";
        // Create the container and return a container client object
        containerClient = blobServiceClient.getBlobContainerClient(containerName);

    }

    /**
     * returns link to image that was saved
     * @param payload
     * @return
     */
    @PostMapping(path = "/addImage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addImageToProfile(@RequestBody Map<String, Object> payload) {

        String title = "no title";
        ObjectId userId=null;
        File file=null;
        String fileContents="";

        //get expected data from map
        for (Map.Entry<String, Object> me : payload.entrySet()) {

            if (me.getKey().equals("userId")){
                userId=new ObjectId(me.getValue().toString());
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
                    Files.write(file.toPath(),Base64.getDecoder().decode(me.getValue().toString()));
                }catch (Exception e) {
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
                .setPermissions(blobPermission);;

        var SAS = blobClient.generateSas(blobServiceSasSignatureValues);

        //blobClient.generateUserDelegationSas()

        String url = blobClient.getBlobUrl();

        var user = userService.findBy_id(userId);
        user.getPublicScreenshotsIDList().add(url); // adds url without sas. wiil have to be generated when retrieving
        userService.save(user);

        var urlWithPermissions = url+"?"+SAS;
        return urlWithPermissions; // link with sas
    }

}
