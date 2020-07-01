package com.ren.renzen.Repos;

import com.ren.renzen.Entities.Content;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface Note_Repository extends CrudRepository<Content, ObjectId> {

    List<Content> findX_NotesByPageSource(String pageSource);
    List<Content> findX_NotesByPageSourceAndNoteType(String pageSource, String noteType);
    List<Content> findX_NotesByParentNote(String parentNote);

}
