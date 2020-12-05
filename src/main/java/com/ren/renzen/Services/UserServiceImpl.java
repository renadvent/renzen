package com.ren.renzen.Services;

import com.ren.renzen.ResourceObjects.DomainObjects.ProfileDO;
import com.ren.renzen.Exceptions.ProfileNotFoundException;
import com.ren.renzen.Exceptions.UserNameAlreadyExistsException;
import com.ren.renzen.Repositories.UserRepository;
import com.ren.renzen.Services.Interfaces.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<ProfileDO> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public ProfileDO save(ProfileDO profileDO) {

        profileDO.setPassword(bCryptPasswordEncoder.encode(profileDO.getPassword()));
        profileDO.setUsername(profileDO.getUsername()); //


        //throw error if name already exists
        if (checkIfUsernameTaken(profileDO.getUsername())) {
            throw new UserNameAlreadyExistsException("Username  '" + profileDO.getUsername() + "' already exists");
        }

        return userRepository.save(profileDO);
    }


    @Override
    public ProfileDO update(ProfileDO profileDO) {

//        profileDO.setPassword(bCryptPasswordEncoder.encode(profileDO.getPassword()));
//        profileDO.setUsername(profileDO.getUsername()); //
//
//
//        //throw error if name already exists
//        if (checkIfUsernameTaken(profileDO.getUsername())) {
//            throw new UserNameAlreadyExistsException("Username  '" + profileDO.getUsername() + "' already exists");
//        }

        return userRepository.save(profileDO);
    }


    @Override
    public List<ProfileDO> getProfileDOList() {
        return userRepository.findAll();
    }

    @Override
    public List<ProfileDO> findAll() {
        return userRepository.findAll();
    }

    @Override
    public ProfileDO findBy_id(ObjectId id) {

        return userRepository.findBy_id(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile with id: " + id + " not found"));
    }


//    @Override
//    public ProfileDO findBy_id(String id) {
//
//        return userRepository.findBy_id(id)
//                .orElseThrow(() -> new ProfileNotFoundException("Profile with id: " + id + " not found"));
//    }

    @Override
    public List<ProfileDO> findAllBy_Id(List<ObjectId> objectIdList) {

        //for deleting, might have to have findAllBy_id return an optional
        //then if a profile is deleted, have it return a dummy object for "deleted user"
        //so server/client doesn't crash

        return userRepository.findAllBy_id(objectIdList);
    }

    @Override
    public boolean checkIfUsernameTaken(String name) {
        return userRepository.findByUsername(name).isPresent();
    }

    @Override
    public ProfileDO findProfileDOByNameAndPassword(String name, String password) {

        Optional<ProfileDO> profileDOOptional = userRepository.findByUsername(name);

        //String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());

        //BCrypt.checkpw(entered_pw, stored_hash)

        if (profileDOOptional.isPresent()) {
            if (BCrypt.checkpw(profileDOOptional.get().getPassword(), password)) {
                System.out.println(profileDOOptional.get().toString());
                return profileDOOptional.get();
            } else {
                throw new RuntimeException("username/password not found");
            }
        } else {
            throw new RuntimeException("username/password not found");
        }
    }

    @Override
    public List<ProfileDO> findAllPage() {
        var paging = PageRequest.of(0, 10, Sort.by("_id").descending());
        return userRepository.findAll(paging).getContent();
    }

    //----------------------------------

    @Override
    public ResponseEntity<?> errorMap(BindingResult result) {
        var errorM = new HashMap<>();

        for (FieldError error : result.getFieldErrors()) {
            errorM.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errorM, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ProfileDO findByUsername(String username) {
        //need to test if exists
        return userRepository.findByUsername(username).get();
    }

}
