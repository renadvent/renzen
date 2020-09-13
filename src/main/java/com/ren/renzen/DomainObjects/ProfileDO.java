package com.ren.renzen.DomainObjects;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * DO for Users
 */
@Data
@Document(collection="Profiles")
@NoArgsConstructor
public class ProfileDO {

    @MongoId
    ObjectId _id;

    @NotBlank(message = "username must not be blank")
            @NotNull(message = "username must not be null")
    String username;
    String password;
    List<ObjectId> articleIDList = new ArrayList<>();
    List<ObjectId> communityIDList = new ArrayList<>();
    List<ObjectId> discussionContentIDs = new ArrayList<>();
    List<ObjectId> articleBookmarkIDList = new ArrayList<>();

    public ProfileDO(String username,String password){
        this.username=username;
        this.password=password;
    }
}
