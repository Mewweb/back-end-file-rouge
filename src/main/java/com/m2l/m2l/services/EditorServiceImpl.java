package com.m2l.m2l.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.repositories.EditorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EditorServiceImpl implements EditorService{
	@Autowired
	private EditorRepository editorRepository;

	@Override
	public List<Editor> findAll(){
		return editorRepository.findAll();
	}
	
	@Override
	public List<Editor> saveAll(List<Editor> editors) {
		return editorRepository.saveAll(editors);
	}
}
