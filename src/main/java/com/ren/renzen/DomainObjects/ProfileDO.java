package com.ren.renzen.DomainObjects;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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

    String username="";
    String password="";
    String email="";
    int phone=0;

    String profilePictureLink="";

    List<ObjectId> friends=new ArrayList<>();

    //toggles for converter
    //public profile view settings toggle (used when converting)
    boolean profileIsPublic = true;
    boolean communityListIsPublic = true;
    boolean articleListIsPublic = true;
    boolean articleBookmarkListIsPublic = false;

    List<ObjectId> likedArticles = new ArrayList<>();

    //lists for contents
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> articleBookmarkIDList = new ArrayList<>();
    List<ObjectId> articleIDList = new ArrayList<>(); // private setting is in article

    List<String> publicScreenshotsIDList = new ArrayList<>();

    //not loaded on profile page unless logged in
    List<String> privateScreenshotIDList = new ArrayList<>();

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
