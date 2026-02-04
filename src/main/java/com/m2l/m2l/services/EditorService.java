package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.m2l.m2l.dto.CreateEditor;
import com.m2l.m2l.entities.Editor;

public interface EditorService {
	Page<Editor> findAll();
	Page<Editor> findAllIdTitle();
	List<Editor> saveAll(List<Editor> editor);	
	Boolean remove(int id);
	Page<Editor> findEditorByTitle(String keyword);
	Page<Editor> findAllWithOffset(int offset);
	ResponseEntity<Editor> createEditor(CreateEditor editor);
}