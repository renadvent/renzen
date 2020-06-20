package com.ren.renzen;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter
@Setter
public class X_Page {

    private @Id
    @GeneratedValue Long id;

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
        com.ren.renzen.X_Page x_page = (com.ren.renzen.X_Page) obj;
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
