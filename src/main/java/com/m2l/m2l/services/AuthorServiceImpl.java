package com.m2l.m2l.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.m2l.m2l.dto.CreateAuthor;
import com.m2l.m2l.entities.Author;
import com.m2l.m2l.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Page<Author> findAll() {
		Page<Author> authors = authorRepository.selectNameAuthor(PageRequest.of(0, 5));
		return authors;
	}

	@Override
	public Page<Author> findAllWithOffset(int offset) {
		Page<Author> authors = authorRepository.findAll(PageRequest.of(offset, 9));
		return authors;
	}

	@Override
	public List<Author> saveAll(List<Author> authors) {
		return authorRepository.saveAll(authors);
	}

	@Override
	public Page<Author> findAuthorByTitle(String keyword) {
		try {
			String urlSearch = URLDecoder.decode(keyword, StandardCharsets.UTF_8.name());
			Page<Author> authors = authorRepository.selectAuthorByTitle(urlSearch, PageRequest.of(0, 9));
			return authors;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	@Override
	@Transactional
	public Boolean remove(int id) {
		Author author = authorRepository.findById(id).orElse(null);
		if (author != null) {
			author.getBooks().clear();
			authorRepository.delete(author);
			return true;
		}
		return false;
	}

	@Override
	public Author update(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public CreateAuthor findById(int id) {
		Author author = authorRepository.findById(id).orElse(null);
		CreateAuthor createAuthor = new CreateAuthor();
		createAuthor.setId(author.getId());
		createAuthor.setLastname(author.getLastname());
		createAuthor.setFirstname(author.getFirstname());
		createAuthor.setLangue(author.getLangue());
		return createAuthor;
	}

	public Author save(CreateAuthor author) {
		try {
			Author a = Author.builder()
					.lastname(author.getLastname())
					.firstname(author.getFirstname())
					.langue(author.getLangue())
					.build();
			return authorRepository.save(a);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Author createAuthor(@RequestBody CreateAuthor request) {
		Author author = Author.builder()
				.lastname(request.getLastname())
				.firstname(request.getFirstname())
				.langue(request.getLangue())
				.build();
		Author savedAuthor = authorRepository.save(author);
		return savedAuthor;
	}
}