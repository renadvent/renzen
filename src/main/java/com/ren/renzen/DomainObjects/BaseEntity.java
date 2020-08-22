package com.ren.renzen.DomainObjects;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter@Setter
public class BaseEntity {
    @Id
    String Id;
    String name;
    String description;
    String creator;
}
