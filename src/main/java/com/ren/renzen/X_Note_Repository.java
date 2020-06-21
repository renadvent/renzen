package com.ren.renzen;

import org.springframework.data.mongodb.repository.MongoRepository;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;


//public interface X_Note_Repository extends CrudRepository<X_Note, Long> {





import java.util.List;


public interface X_Note_Repository extends MongoRepository<com.ren.renzen.X_Note, String> {

    //set up a simple query
    //public X_Note findByFirstName(String firstName);


    //public List<X_Note> findByUser (String lastName);

}

//public interface X_Note_Repository extends PagingAndSortingRepository<X_Note, Long> {
//
//}
