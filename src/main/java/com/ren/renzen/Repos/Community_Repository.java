package com.ren.renzen.Repos;

import com.ren.renzen.DomainObjects.Community;
import org.springframework.data.repository.CrudRepository;

public interface Community_Repository extends CrudRepository<Community,String> {
    Community findByName(String name);
}
