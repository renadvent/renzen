package com.ren.renzen.Repos;

//import org.springframework.data.mongodb.repository.MongoRepository;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;


//public interface X_Note_Repository extends CrudRepository<X_Note, Long> {





import com.ren.renzen.Entities.X_Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


//public interface X_Note_Repository extends MongoRepository<com.ren.renzen.Entities.X_Note, String> {

    //set up a simple query
    //public X_Note findByFirstName(String firstName);


    //public List<X_Note> findByUser (String lastName);

//}

public interface X_Note_Repository extends MongoRepository<X_Note, String> {

}
