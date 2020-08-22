package com.ren.renzen.Repos;

import com.ren.renzen.DomainObjects.ArticleDomainObject;
import org.springframework.data.repository.CrudRepository;

public interface Article_Repository extends CrudRepository<ArticleDomainObject,String> {
}
