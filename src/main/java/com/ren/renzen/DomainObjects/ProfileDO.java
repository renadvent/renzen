package com.ren.renzen.DomainObjects;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * DO for Users
 */
@Data
@Document(collection = "Profiles")
@NoArgsConstructor
public class ProfileDO implements UserDetails {

    @MongoId
    ObjectId _id;

    @NotBlank(message = "username must not be blank")
    String username;
    @NotBlank(message = "password must not be blank")
    String password;

//    @Email(meessage="need to be an email")
//    String email;

//    private Date created_At;
//    private Date updated_At;


    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> discussionContentIDs = new ArrayList<>();
    List<ObjectId> articleBookmarkIDList = new ArrayList<>();

    List<String> screenshotsIDList = new ArrayList<>();

    public ProfileDO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*
    User details implment methods
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
