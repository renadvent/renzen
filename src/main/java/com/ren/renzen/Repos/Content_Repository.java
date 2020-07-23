package com.ren.renzen.Repos;

import com.ren.renzen.Entities.Content;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface Content_Repository extends CrudRepository<Content, String> {
}
