package com.ren.renzen.Repos;

import com.ren.renzen.Entities.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Section_Repository extends CrudRepository<Section,String> {
    //List<Section> findSectionsBy
}
