package BackEndRewrite.Controllers;

import com.ren.renzen.Repos.Article_Repository;
import com.ren.renzen.Repos.Community_Repository;
import com.ren.renzen.Repos.User_Repository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
public class SiteController {
    final User_Repository user_repository;
    final Community_Repository community_repository;
    final Article_Repository article_repository;

    public SiteController(User_Repository user_repository, Community_Repository community_repository, Article_Repository article_repository) {
        this.user_repository = user_repository;
        this.community_repository = community_repository;
        this.article_repository = article_repository;
    }

    @RequestMapping
    public String index(){
        return "index";
    }


}
