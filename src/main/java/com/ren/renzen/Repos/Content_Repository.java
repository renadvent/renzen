package com.ren.renzen.Repos;

import com.ren.renzen.Entities.Content;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

//public interface Content_Repository extends PagingAndSortingRepository<Content, String> {
public interface Content_Repository extends CrudRepository<Content, String> {

//    List<Content> findX_NotesByPageSource(String pageSource);
//    List<Content> findX_NotesByPageSourceAndNoteType(String pageSource, String noteType);
//    List<Content> findX_NotesByParentNote(String parentNote);

}
