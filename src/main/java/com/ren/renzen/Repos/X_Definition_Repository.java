package com.ren.renzen.Repos;

import com.ren.renzen.Entities.Definition;
import com.ren.renzen.Entities.X_Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface X_Definition_Repository extends MongoRepository<Definition, String> {
}

