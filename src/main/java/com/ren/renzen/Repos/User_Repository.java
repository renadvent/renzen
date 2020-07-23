package com.ren.renzen.Repos;

import com.ren.renzen.Entities.Section;
import com.ren.renzen.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface User_Repository extends CrudRepository<User,String> {
    User findByUserName(String userName);
}
