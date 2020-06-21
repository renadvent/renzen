package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter@Setter
public class X_Note {

    private @Id
 Long id;

    private String content;
    private String date;
    private String time;
    private String noteType;
    private String user;

    private X_Note () {};

    public X_Note(String content, String user) {
        this.content = content;
        this.user= user;
    }

    public X_Note(String content) {
        this.content = content;

    }

}
