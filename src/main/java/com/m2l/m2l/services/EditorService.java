package com.m2l.m2l.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.m2l.m2l.dto.CreateEditor;
import com.m2l.m2l.entities.Editor;

public interface EditorService {
	Page<Editor> findAll();

	Page<Editor> findAllIdTitle();

	CreateEditor findById(int id);

	List<Editor> saveAll(List<Editor> editor);

	Boolean remove(int id);

	Editor update(Editor editor);

	Page<Editor> findEditorByTitle(String keyword);

	Page<Editor> findAllWithOffset(int offset);

	Editor createEditor(CreateEditor editor);
}