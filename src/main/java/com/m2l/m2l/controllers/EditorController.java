package com.m2l.m2l.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.m2l.m2l.dto.CreateEditor;
import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.services.EditorService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/editor")
@AllArgsConstructor
public class EditorController {
	private EditorService editorService;

	@GetMapping("/all")
	public ResponseEntity<Page<Editor>> findAll() {
		Page<Editor> allEditors = editorService.findAll();
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}

	@GetMapping("/admin/all")
	public ResponseEntity<Page<Editor>> findAllIdTitle() {
		Page<Editor> allEditors = editorService.findAllIdTitle();
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}

	@GetMapping("/all/{offset}")
	public ResponseEntity<Page<Editor>> findAllWithOffset(@PathVariable int offset) {
		Page<Editor> allEditors = editorService.findAllWithOffset(offset);
		return new ResponseEntity<Page<Editor>>(allEditors, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CreateEditor> getEditor(@PathVariable int id) {
		var e = editorService.findById(id);
		if (e == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<CreateEditor>(e, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Void> deleteEditor(@PathVariable int id) {
		var e = editorService.remove(id);
		if (e == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addEditor(@RequestPart CreateEditor editor) {
		try {
			Editor createEditor = editorService.createEditor(editor);
			return new ResponseEntity<>(createEditor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Editor> updateEditor(@RequestBody Editor editor, @PathVariable int id) {
		if (id != editor.getId()) {
			return ResponseEntity.badRequest().build();
		}
		var e = editorService.update(editor);
		if (e == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Editor>(e, HttpStatus.ACCEPTED);
	}
}
