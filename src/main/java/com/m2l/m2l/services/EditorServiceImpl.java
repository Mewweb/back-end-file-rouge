package com.m2l.m2l.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.m2l.m2l.dto.CreateEditor;
import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.repositories.EditorRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EditorServiceImpl implements EditorService{
	@Autowired
	private EditorRepository editorRepository;

	@Override
	public Page<Editor> findAll(){
		Page<Editor> editors = editorRepository.selectTitleEditor(PageRequest.of(0, 9));
		return editors;
	}
	
	@Override
	public Page<Editor> findAllIdTitle(){
		Page<Editor> editors = editorRepository.selectNameEditor(PageRequest.of(0, 9));
		return editors;
	}
	
	@Override
	public Page<Editor> findEditorByTitle(String keyword){
		try {
			String urlSearch = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
			Page<Editor> editors = editorRepository.selectEditorByTitle(urlSearch, PageRequest.of(0, 9));
			return editors;
		}catch(UnsupportedEncodingException e) {
			return null;
		}
	}
	
	@Override
	public CreateEditor findById(int id) {
		Editor editor = editorRepository.findById(id).orElse(null);
		CreateEditor createEditor = new CreateEditor();
		createEditor.setId(editor.getId());
		createEditor.setTitle(editor.getTitle());
		createEditor.setDescription(editor.getDescription());
		createEditor.setDate(editor.getDate().toString());
		return createEditor;
	}
	
	@Override
	public Editor update(Editor editor) {
		return editorRepository.save(editor);
	}
	
	@Override
	@Transactional
	public Boolean remove(int id) {
		Editor editor = editorRepository.findById(id).orElse(null);
		if(editor != null) {
			editor.getArticles().clear();
			editorRepository.delete(editor);
			return true;
		}
		return false;
	}
	
	@Override
	public List<Editor> saveAll(List<Editor> editors) {
		return editorRepository.saveAll(editors);
	}
	
	@Override
	public Page<Editor> findAllWithOffset(int offset){
		Page<Editor> editors = editorRepository.findAll(PageRequest.of(offset, 9));
		return editors;
	}
	
	public ResponseEntity<Editor> createEditor(@RequestBody CreateEditor request){
		Editor editor = Editor.builder()
				.title(request.getTitle())
				.description(request.getDescription())
				.date(LocalDate.parse(request.getDate()))
				.build();
		Editor savedEditor = editorRepository.save(editor);
		return ResponseEntity.ok(savedEditor);
	}
}