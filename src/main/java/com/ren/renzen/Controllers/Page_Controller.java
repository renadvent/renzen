package com.ren.renzen.Controllers;


import com.ren.renzen.Entities.Definition;
import com.ren.renzen.Entities.X_Note;
import com.ren.renzen.Entities.X_Page;
import com.ren.renzen.Repos.X_Page_Repository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@RestController
@RequestMapping("/Pages")
public class Page_Controller {

    X_Page_Repository mongoRep;

    Page_Controller(X_Page_Repository mongoRep){
        this.mongoRep=mongoRep;
    }

    @Field(value="definitions")
    List<X_Note> definitions;

    @Field(value="walkthroughs")
    List<X_Note> walkthroughs;

//    @PostMapping()
//    public String createPage(@RequestBody X_Page page){
//        X_Page x = this.mongoRep.save(page);
//        return "New Page "+x.getPageName();
//    }

//    @PostMapping()
//    public Optional<X_Page> getPageByID(String id){
//        return mongoRep.findById(id);
//    }

//
//    @GetMapping("/{id}")
//    public Optional<X_Page> loadPageContent(@PathVariable(value = "id") String pageId){
//
////        //get a list of definitions
////        return null;
//
//    }

}
