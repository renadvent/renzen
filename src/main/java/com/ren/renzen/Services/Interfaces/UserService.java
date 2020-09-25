package com.ren.renzen.Services.Interfaces;

import com.ren.renzen.DomainObjects.ProfileDO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface UserService {
    ProfileDO save(ProfileDO profileDO);

    List<ProfileDO> getProfileDOList();

    List<ProfileDO> findAll();

    ProfileDO findBy_id(ObjectId id);

    List<ProfileDO> findAllBy_Id(List<ObjectId> objectIdList);

    boolean checkIfUsernameTaken(String name);

    ProfileDO findProfileDOByNameAndPassword(String name, String password);

    List<ProfileDO> findAllPage();

    ResponseEntity<?> errorMap(BindingResult result);
}
