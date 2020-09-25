package com.ren.renzen.Controllers;

import com.ren.renzen.DomainObjects.ImageDO;
import com.ren.renzen.Services.Interfaces.*;
import lombok.Data;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

@RestController
public class ImageController {
    final UserService userService;
    final ArticleService articleService;
    final DiscussionService discussionService;
    final CommunityService communityService;

    final ImageService imageService;

    public ImageController(UserService userService, ArticleService articleService, DiscussionService discussionService, CommunityService communityService, ImageService imageService) {
        this.userService = userService;
        this.articleService = articleService;
        this.discussionService = discussionService;
        this.communityService = communityService;
        this.imageService = imageService;
    }

    @GetMapping(path = "/getImage")
    public String getImageTest(){
        ImageDO imageDO = imageService.getImage("5f6e49fc5abf1a24db808a01").get();
        var x = Base64.getEncoder().encode(imageDO.getImage().getData());
        try {
            File file = File.createTempFile("image", ".png");
            Path path = file.toPath();
            //ImageIO.write(imageDO.getImage().getData(),"png",file);
            Files.write(path,imageDO.getImage().getData());
            //Files.write(path,x);
            //Files.write(path,x.getBytes());



            System.out.println();
            return file.getAbsolutePath();
            //ImageIO.write(bi, "png", file);
        }catch (Exception exception){
            //
        }

        return "failed";
    }

    @PostMapping(path = "/addImage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addImageToProfile(@RequestBody Map<String, Object> payload) {
        String title = "";
        Binary bin = null;

        //get expected data from map
        for (Map.Entry<String, Object> me : payload.entrySet()) {

            if (me.getKey().equals("title")) {
                title = (String) ((ArrayList) me.getValue()).get(0);
            }

            if (me.getKey().equals("file")) {
                var pngAsString = (String) (((ArrayList) me.getValue()).get(0));
                bin = new Binary(BsonBinarySubType.BINARY, pngAsString.getBytes());
                System.out.println(bin);
                System.out.println("done");
            }
        }

        //put data on object
        ImageDO imageDO = new ImageDO();
        imageDO.setTitle(title);
        imageDO.setImage(bin);

        imageService.save(imageDO);

        return true;
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
