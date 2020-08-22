package com.ren.renzen.Repos;

import com.ren.renzen.DomainObjects.UserDomainObject;
import org.springframework.data.repository.CrudRepository;

public interface User_Repository extends CrudRepository<UserDomainObject,String> {
        UserDomainObject findByUserName(String userName);
}
