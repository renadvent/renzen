package com.ren.renzen;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


//public interface X_Note_Repository extends CrudRepository<X_Note, Long> {

public interface X_Note_Repository extends PagingAndSortingRepository<X_Note, Long> {

}
