package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter@Setter
@Document(collection = "Communities")
public class Community extends BaseEntity {

    public Community(){}

    public Community(String name, String description) {
        this.name=name;
        this.description=description;
    }

}
