package com.ren.renzen.Repos;

import com.ren.renzen.Entities.X_Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

//@RepositoryRestResource(exported=true, path="Notes")
//@RepositoryRestResource(collectionResourceRel = "notes", path = "notes")
public interface X_Note_Repository extends CrudRepository<X_Note, ObjectId> {
//public interface X_Note_Repository extends CrudRepository<X_Note, String> {
//public interface X_Note_Repository extends MongoRepository<X_Note, String>{
//public interface X_Note_Repository extends MongoRepository<X_Note, String>{

    List<X_Note> findX_NotesByPageSource(String pageSource);
    //X_Note findX_NoteById(String id);

}
