package com.ren.renzen.ResourceObjects.DomainObjects;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

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

    Set<ObjectId> friends = new HashSet<>();

    Set<ObjectId> likedArticles = new HashSet<>();
    Set<ObjectId> dislikedArticles = new HashSet<>();

    //lists for contents
    Set<ObjectId> createdCommunityIDList = new HashSet<>();
    Set<ObjectId> joinedCommunityIDList = new HashSet<>();
    Set<ObjectId> articleBookmarkIDList = new HashSet<>();

    ObjectId noneCommunity;

    Set<ObjectId> articleIDList = new HashSet<>();
    Set<ObjectId> articleDraftIDList = new HashSet<>();

    Set<String> workNames = new HashSet<>();

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
