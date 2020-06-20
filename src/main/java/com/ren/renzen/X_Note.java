package com.ren.renzen;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter@Setter@Entity
public class X_Note {

    private @Id
    @GeneratedValue Long id;

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

}
