package com.ren.renzen.Controllers;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.ren.renzen.DomainObjects.ImageDO;
import com.ren.renzen.Services.Interfaces.*;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
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
        String connectStr="DefaultEndpointsProtocol=https;AccountName=renzenblob;AccountKey=75sYRhsaFVIOhqGeOzKkm4YqRMTkFFCavrg2WSuVR64lT/tKJSQM5j/HQUdkYcxuWuyx61BI47u+2VBjYhp4rw==;EndpointSuffix=core.windows.net";
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        //Create a unique name for the container
        String containerName = "renzen-test";

        // Create the container and return a container client object
        containerClient = blobServiceClient.getBlobContainerClient(containerName);

    }

    @GetMapping(path = "/getImage")
    public String getImageTest(String id){

//        // Download the blob to a local file
//// Append the string "DOWNLOAD" before the .txt extension so that you can see both files.
//        String downloadFileName = "downloadeddddd.png"//fileName.replace(".txt", "DOWNLOAD.txt");
//        File downloadedFile = new File("C:/"+downloadFileName);
//
//        System.out.println("\nDownloading blob to\n\t " + localPath + downloadFileName);
//
//        blobClient.downloadToFile(localPath + downloadFileName);





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


//                    var x = new BlobServiceSasSignatureValues()
//                            .setProtocol(SasProtocol.HTTPS_ONLY) // Users MUST use HTTPS (not HTTP).
//                            .setExpiryTime(OffsetDateTime.now().plusDays(2))
//                            .setContainerName("my-container")
//                            .setBlobName("HelloWorld.txt")
//                            .setPermissions(blobPermission);

//                    SharedAccessAccountPolicy sharedAccessAccountPolicy = new SharedAccessAccountPolicy();
//                    sharedAccessAccountPolicy.setPermissionsFromString("racwdlup");
//                    long date = new Date().getTime();
//                    long expiryDate = new Date(date + 8640000).getTime();
//                    sharedAccessAccountPolicy.setSharedAccessStartTime(new Date(date));
//                    sharedAccessAccountPolicy.setSharedAccessExpiryTime(new Date(expiryDate));
//                    sharedAccessAccountPolicy.setResourceTypeFromString("sco");
//                    sharedAccessAccountPolicy.setServiceFromString("bfqt");
//                    String sasToken = "?" + storageAccount.generateSharedAccessSignature(sharedAccessAccountPolicy);
//



//                    String url2 = blobClient.

                    String url = blobClient.getBlobUrl();

                    return url;

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
