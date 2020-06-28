package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;


@Getter
@Setter
@Document(collection="Pages")
public class X_Page {


    @Id
    private String pageName;
    private String description;

    @Field(value="defs")
    private List<String> defs;

    @Field(value="QAs")
    private List<String> QAs;

//    public class QA{
//        String qTitle;
//        String qContent;
//
//        public class Answers
//    }


    private X_Page(){}

    public X_Page(String pageName, String description){
        this.pageName=pageName;
        this.description=description;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        X_Page x_page = (X_Page) obj;
        return Objects.equals(pageName,x_page.pageName) &&
                Objects.equals(pageName,x_page.pageName) && Objects.equals(description,x_page.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageName,pageName,description);
    }

    @Override
    public String toString() {
        return "Page{"+
                "pageName=" +pageName +'\''+
                "description="+description +'\'' +
                "}";
    }
}
