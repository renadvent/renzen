package com.ren.renzen.Repositories;

import com.ren.renzen.DomainObjects.ImageDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<ImageDO, String> {
}
