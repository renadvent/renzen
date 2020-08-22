package com.ren.renzen.DTOs.User;

import com.ren.renzen.DTOs.BaseInfo;
import com.ren.renzen.Entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserProfilePageDTO {

    String id;
    String name;

    //Just the names and links [not contents]
    BaseInfo communities;
    BaseInfo articles;

    //converts user object into info needed for user page
    UserProfilePageDTO(User user){
        this.id=user.getId();
        this.name=user.getName();

        communities = new BaseInfo(user.getCommunities());
        articles = new BaseInfo(user.getArticles());
    }
}
