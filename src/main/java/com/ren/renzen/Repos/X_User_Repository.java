package com.ren.renzen.Repos;

import com.ren.renzen.Entities.X_Note;
import com.ren.renzen.Entities.X_User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface X_User_Repository extends MongoRepository<X_User, Long> {
}
