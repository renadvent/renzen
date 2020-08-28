package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.ModelAssemblers.TestAssembler;
import BackEndRewrite.Services.UserServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO move logic to ArticleService

@RestController
public class CreateArticleController {

    final UserServiceImpl userService;

    //test
    final TestAssembler assembler;

    public CreateArticleController(UserServiceImpl userService, TestAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    //uselessly used in test assembler
    public Class<?> doNothing(){
        return null;
    }

    @PostMapping(path="/articles/createArticle")
    public HttpEntity<ArticleComponentCO> createArticle(@RequestBody CreateArticlePayload payload){

        //set initial DO attributes
        ArticleDO articleDO = new ArticleDO();
        articleDO.setName(payload.getName());
        articleDO.setDescription(payload.getDescription());
        articleDO.setUserID(payload.getAuthorID());

        //add article to user profile
        userService.findProfileDOById(articleDO.getUserID())
                .map(profileDO->{
                    profileDO.getArticleIDList().add(articleDO.getId());
                    return true;
                    //return new ResponseEntity(HttpStatus.OK); //?
                }).orElse(false);

        //TODO create a blank discussion section for the article
        articleDO.setDiscussionID("");

        //TODO distribute article sections





        return new ResponseEntity(HttpStatus.OK);
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
