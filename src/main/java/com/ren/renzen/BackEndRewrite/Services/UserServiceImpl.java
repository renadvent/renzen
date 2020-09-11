package com.ren.renzen.BackEndRewrite.Services;

import com.ren.renzen.BackEndRewrite.DomainObjects.ProfileDO;
import com.ren.renzen.BackEndRewrite.Repositories.UserRepository;
import com.ren.renzen.BackEndRewrite.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ProfileDO save(ProfileDO profileDO) {
        return userRepository.save(profileDO);
    }

    @Override
    public Iterable<ProfileDO> getProfileDOList() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<ProfileDO> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ProfileDO findBy_id(ObjectId id) {
        return userRepository.findBy_id(id);
    }

    @Override
    public List<ProfileDO> findAllBy_Id(List<ObjectId> objectIdList){


        //for deleting, might have to have findAllBy_id return an optional
        //then if a profile is deleted, have it return a dummy object for "deleted user"
        //so server/client doesn't crash
        

        //var test = userRepository.findAllBy_id(objectIdList);




        return userRepository.findAllBy_id(objectIdList);
    }

    @Override
    public ProfileDO findProfileDOByName(String profileName) {

        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(profileName);

        if (profileDOOptional.isEmpty()){
            throw new ResourceNotFoundException("id not found");
        }else {
            System.out.println(profileDOOptional.get());
            return profileDOOptional.get();
        }

    }

    @Override
    public boolean checkIfUsernameTaken(String name) {

        return userRepository.findByUsername(name).isPresent();
    }

    @Override
    public ProfileDO findProfileDOByNameAndPassword(String name, String password) {


        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(name);

        if (profileDOOptional.isPresent()){
            if (profileDOOptional.get().getPassword().equals(password)){
                System.out.println(profileDOOptional.get().toString());
                return profileDOOptional.get();
            }else{
                throw new RuntimeException("username/password not found");
            }
        }else{
            throw new RuntimeException("username/password not found");
        }
    }

    @Override
    public ProfileDO saveAndReturnProfileDO(ProfileDO profileDO) {
        return userRepository.save(profileDO);
    }

    @Override
    public List<ProfileDO> findAllPage() {
        var paging = PageRequest.of(0,10, Sort.by("_id"));
        return userRepository.findAll(paging).getContent();
    }

}
