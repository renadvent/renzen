package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.X_Note;
import com.ren.renzen.Repos.X_Note_Repository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter@Setter
@RestController
@RequestMapping("/Notes")
public class NoteController {

    private X_Note_Repository mongoRepo;

    public NoteController(X_Note_Repository mongoRepo){
        this.mongoRepo=mongoRepo;
    }

    @GetMapping()
    public List<X_Note> getNotes(){
        return this.mongoRepo.findAll();
    }


    //mapping to retrieve user content on page
    @GetMapping(path="/{id}", produces = "application/json")
    public List<X_Note> getSelectNotes(@PathVariable(value="id") String pageId){

//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").regex("^A"));
//        List<X_Note> notes = mongoRepo.find(query,X_Note.class);

        //no

        //mongoRepo.findBy

        return this.mongoRepo.findX_NotesByPageSource(pageId);
        //return this.mongoRepo.findAll();//findByPageSource(pageId);
    }







    @PostMapping()
    public String postNote(@RequestBody X_Note note){

        X_Note x = mongoRepo.save(note);

        x.getContent();

        return ("Note Content: "+x.getContent());
    }


}
