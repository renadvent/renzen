package com.ren.renzen.ResourceObjects.DomainObjects;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * DO for Users
 */
@Data
@NoArgsConstructor
@Document(collection = "Profiles")
public class ProfileDO implements UserDetails {

    @MongoId
    ObjectId _id;

    String username = "";
    String password = "";
    String email = "";
    int phone = 0;

    String profilePictureLink = "";

    List<ObjectId> friends = new ArrayList<>();

    List<ObjectId> likedArticles = new ArrayList<>();
    List<ObjectId> dislikedArticles = new ArrayList<>();

    //lists for contents
    List<ObjectId> createdCommunityIDList = new ArrayList<>();
    List<ObjectId> joinedCommunityIDList = new ArrayList<>();
    List<ObjectId> articleBookmarkIDList = new ArrayList<>();

    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> articleDraftIDList = new ArrayList<>();

    List<String> workNames = new ArrayList<>();

    Date created_at = new Date();
    List<Date> updated_at = new ArrayList<>();

    List<Date> logins_at = new ArrayList<>();

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
