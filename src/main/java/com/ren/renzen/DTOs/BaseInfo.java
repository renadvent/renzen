package com.ren.renzen.DTOs;

import com.ren.renzen.Entities.BaseEntity;
import com.ren.renzen.Entities.Community;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class BaseInfo<T extends BaseEntity> {
    List<String> names=new ArrayList<>();
    List<String> links=new ArrayList<>();

    public BaseInfo(){}

    public BaseInfo(List<T> list){
        for (T t : list){
            names.add(t.getName());
            links.add(t.getId());
        }
    }
}
