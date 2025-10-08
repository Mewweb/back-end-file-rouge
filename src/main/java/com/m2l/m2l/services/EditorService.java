package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Editor;

public interface EditorService {
	List<Editor> findAll();
	
	List<Editor> saveAll(List<Editor> editor);
}
