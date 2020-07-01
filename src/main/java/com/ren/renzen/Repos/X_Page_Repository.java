package com.ren.renzen.Repos;

import com.ren.renzen.Entities.X_Note;
import com.ren.renzen.Entities.X_Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface X_Page_Repository extends CrudRepository<X_Page, String> {
}
