package com.m2l.m2l.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.m2l.m2l.entities.Editor;

public interface EditorRepository extends JpaRepository<Editor, Integer> {
	@Query("""
			SELECT DISTINCT title FROM Editor e
			""")
	Page<Editor> selectTitleEditor(Pageable pageable);
	
	@Query(""" 
			SELECT DISTINCT id,title FROM Editor e
			""")
	Page<Editor> selectNameEditor(Pageable pageable);
	
	@Query(""" 
			SELECT DISTINCT title FROM Editor e WHERE
			LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
	Page<Editor> selectEditorByTitle(@Param("keyword") String keyword, Pageable pageable);
	
}