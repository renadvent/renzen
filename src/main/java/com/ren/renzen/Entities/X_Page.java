package com.ren.renzen.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;


@Getter
@Setter
public class X_Page {

    private @Id
    Long id;

    private String pageName;
    private String description;

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
        return Objects.equals(id,x_page.id) &&
                Objects.equals(pageName,x_page.pageName) && Objects.equals(description,x_page.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,pageName,description);
    }

    @Override
    public String toString() {
        return "Page{"+
                "pageName=" +pageName +'\''+
                "description="+description +'\'' +
                "}";
    }
}
