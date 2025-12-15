package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.m2l.m2l.entities.Editor;

public interface EditorService {
	Page<Editor> findAll();
	List<Editor> saveAll(List<Editor> editor);	
	Page<Editor> findEditorByTitle(String keyword);
}