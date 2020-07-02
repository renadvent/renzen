package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter@Setter@Document(value = "Sections")
public class Section {

    @Id
    private String id;

    private String question_ref;
    private List<String> answer_refs;

    public Section() {}

    //create new section with question_ref
    public Section(String question_ref){
        this.question_ref=question_ref;
    }
}
