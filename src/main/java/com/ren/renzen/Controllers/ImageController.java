package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.ren.renzen.DomainObjects.ImageDO;
import com.ren.renzen.Services.Interfaces.*;
import com.ren.renzen.additional.KEYS;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Map;

@RestController
public class ImageController {
    final UserService userService;
    final ArticleService articleService;
    final DiscussionService discussionService;
    final CommunityService communityService;

    final ImageService imageService;

    BlobServiceClient blobServiceClient;
    BlobContainerClient containerClient;

    public ImageController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ImageService imageService) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
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

    @GetMapping(path = "/getImage")
    public String getImageTest(String id){

        ImageDO imageDO = imageService.getImage("5f6e49fc5abf1a24db808a01").get();

        try {
            File file = File.createTempFile("image", ".png");
            Files.write(file.toPath(),Base64.getDecoder().decode(imageDO.getImage()));
            System.out.println();
            return file.getAbsolutePath();
        }catch (Exception exception){
            //
        }

        return "failed";
    }

    /**
     * returns link to image that was saved
     * @param payload
     * @return
     */
    @PostMapping(path = "/addImage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addImageToProfile(@RequestBody Map<String, Object> payload) {

        String title = "";

        //get expected data from map
        for (Map.Entry<String, Object> me : payload.entrySet()) {

            if (me.getKey().equals("title")) {
                title = (String) me.getValue();
            }

            if (me.getKey().equals("file")) {
                try {
                    ImageDO imageDO= new ImageDO();
                    imageDO.setTitle(title);
                    imageDO.setImage(me.getValue().toString());
                    imageService.save(imageDO);

                    //test rewrite
                    File file = File.createTempFile("image", ".png");
                    Files.write(file.toPath(),Base64.getDecoder().decode(me.getValue().toString()));

                    //upload to azure?????
                    // Get a reference to a blob
                    BlobClient blobClient = containerClient.getBlobClient(file.getName());
                    blobClient.uploadFromFile(file.getAbsolutePath());


                    file.delete();

                    System.out.println("rewritten file");
                    System.out.println(file.getAbsoluteFile());

                    BlobSasPermission blobPermission = new BlobSasPermission().setReadPermission(true);

                    var x = new BlobServiceSasSignatureValues()
                            .setProtocol(SasProtocol.HTTPS_ONLY) // Users MUST use HTTPS (not HTTP).
                            .setExpiryTime(OffsetDateTime.now().plusDays(2))
                            .setPermissions(blobPermission);;

                    var y = blobClient.generateSas(x);

                    String url = blobClient.getBlobUrl();

                    var withper = url+"?"+y;
                    return withper; // link with sas

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //
        return "-=-";
    }

    @Data
    public static class imageUpload {
        String title;
        MultipartFile file;

        imageUpload(String title, MultipartFile file) {
            this.title = title;
            this.file = file;
        }
    }
}
