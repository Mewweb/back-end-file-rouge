package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.services.EditorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/editor")
@AllArgsConstructor
public class EditorController {
	private EditorService editorService;
	
	@GetMapping("/all")
	public ResponseEntity<Page<Editor>> findAll(){
		Page<Editor> allEditors = editorService.findAll();
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}
	
	@GetMapping("/admin/all")
	public ResponseEntity<Page<Editor>> findAllIdTitle(){
		Page<Editor> allEditors = editorService.findAllIdTitle();
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}
	
	@GetMapping("/{keyword}")
	public ResponseEntity<Page<Editor>> findByTitle(@PathVariable String keyword){
		Page<Editor> allEditors = editorService.findEditorByTitle(keyword);
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<Page<Editor>> findAllOrFindByTitle(@PathVariable String title){
		Page<Editor> allEditors = editorService.findEditorByTitle(title);
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}
}
