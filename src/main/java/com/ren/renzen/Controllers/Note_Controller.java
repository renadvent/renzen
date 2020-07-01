package com.ren.renzen.Controllers;

import com.ren.renzen.Entities.Content;
import com.ren.renzen.Repos.Note_Repository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;


//this works now
//localhost:8001/api/pageName?pageName=default

//@RestController
//@RequestMapping("/Notes")
@Getter@Setter
//@RepositoryRestController()
@BasePathAwareController
public class Note_Controller {

    private Note_Repository mongoRepo;

    public Note_Controller(Note_Repository mongoRepo){
        this.mongoRepo=mongoRepo;
    }

//    @GetMapping//(path="/Notes")
//    public Iterable<X_Note> getNotes(){
//        return this.mongoRepo.findAll();
//    }

//    @RequestMapping(value="/pageName")
//    public List<X_Note> getNotesByPage(@RequestParam("pageName") String pageName){
//        System.out.println("proc");
//        return this.mongoRepo.findX_NotesByPageSource(pageName);
//    }

    @GetMapping( value="/a")
    public @ResponseBody Iterable<Content> getNotesByPage(){

        //return ("hi");
        return mongoRepo.findAll();

    }

    @GetMapping(value="/pageSource")
    public @ResponseBody Iterable<Content> getNotesByPage(@RequestParam("pageSource") String pageSource){
        System.out.println("proc");
        //return ("HI");
        return this.mongoRepo.findX_NotesByPageSource(pageSource);
    }

    @GetMapping(value="/section")
    public @ResponseBody Iterable<Content> getNotesByPage(
            @RequestParam("pageSource") String pageSource,
            @RequestParam("noteType") String noteType ){
        System.out.println("proc");
        //return ("HI");
        return this.mongoRepo.findX_NotesByPageSourceAndNoteType(pageSource,noteType);
    }

    @GetMapping(value="/getChildren")
    public @ResponseBody Iterable<Content> getByParent(
            @RequestParam("parent") String parent){
        return this.mongoRepo.findX_NotesByParentNote(parent);
    }


    //mapping to retrieve user content on page
    //@GetMapping(path="x_Notes/{id}")
//    @RestResource(path="notes")
//    public List<X_Note> getSelectNotes(@Param("name") String pageId){
//        System.out.println("retrieving notes by page id");
//        return this.mongoRepo.findX_NotesByPageSource(pageId);
//    }

//    @PatchMapping(path="/{id}")
//    public X_Note updateNote(@PathVariable(value="id") String noteID){
//        mongoRepo.save(mongoRepo.findX_NoteById(noteID));
//        return mongoRepo.findX_NoteById(noteID);
//    }


//    @PatchMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void updateNote(
//            @RequestBody Map<String,X_Note> updates,
//            @PathVariable(value = "id") String id){
//
//        mongoRepo.
//
//        this.mongoRepo.save(updates,id);
//        //X_Note n = mongoRepo.findX_NoteById(id);
//        //mongoRepo.save(note);
//    }

//    @PostMapping()
//    public String postNote(@RequestBody X_Note note){
//
//        X_Note x = mongoRepo.save(note);
//
//        x.getContent();
//
//        return ("Note Content: "+x.getContent());
//    }


}
