package BackEndRewrite.Controllers;

import BackEndRewrite.CommandObjects.SubCommunityComponentCOs.ArticleComponentCO;
import BackEndRewrite.DomainObjects.ArticleDO;
import BackEndRewrite.Services.UserServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//TODO move logic to ArticleService

@Controller
public class CreateArticleController {

    final UserServiceImpl userService;

    public CreateArticleController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping(path="/articles/createArticle")
    @ResponseBody
    public ArticleComponentCO createArticle(@RequestBody CreateArticlePayload payload){

        //set initial DO attributes
        ArticleDO articleDO = new ArticleDO();
        articleDO.setName(payload.getName());
        articleDO.setDescription(payload.getDescription());
        articleDO.setUserID(payload.getAuthorID());

        //add article to user profile
        userService.findProfileDOById(articleDO.getUserID())
                .map(profileDO->{
                    profileDO.getArticleIDList().add(articleDO.getId());
                    return true; //?
                }).orElse(false);

        //TODO create a blank discussion section for the article
        articleDO.setDiscussionID("");

        //TODO distribute article sections





        return null;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public class CreateArticlePayload {
        String name;
        String description;
        String authorID;
    }

}
