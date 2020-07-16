package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter@Setter
@Document(collection = "StudyGuides")
public class StudyGuide {

    @Id
    private String id;
    private List<String> content_refs;

    public StudyGuide() {}

    public StudyGuide(String href){
        this.content_refs.add(href);
    }
}
