package com.ren.renzen.Services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.sas.BlobSasPermission;
import com.azure.storage.blob.sas.BlobServiceSasSignatureValues;
import com.azure.storage.common.sas.SasProtocol;
import com.ren.renzen.DomainObjects.ProfileDO;
import com.ren.renzen.Exceptions.ProfileNotFoundException;
import com.ren.renzen.Repositories.UserRepository;
import com.ren.renzen.Services.Interfaces.UserService;
import com.ren.renzen.additional.KEYS;
import org.bson.types.ObjectId;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.OffsetDateTime;
import java.util.HashMap;
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

        if (profileDOOptional.isPresent()) {
            if (profileDOOptional.get().getPassword().equals(password)) {
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

}
