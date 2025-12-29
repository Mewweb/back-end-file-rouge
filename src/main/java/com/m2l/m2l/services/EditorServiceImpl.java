package com.m2l.m2l.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<Editor> findAll(){
		Page<Editor> editors = editorRepository.selectTitleEditor(PageRequest.of(0, 9));
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
	public List<Editor> saveAll(List<Editor> editors) {
		return editorRepository.saveAll(editors);
	}
}