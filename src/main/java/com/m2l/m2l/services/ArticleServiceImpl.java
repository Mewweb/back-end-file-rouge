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
import com.m2l.m2l.dto.CreateArticle;
import com.m2l.m2l.entities.Article;
import com.m2l.m2l.entities.Book;
import com.m2l.m2l.entities.Editor;
import com.m2l.m2l.repositories.ArticleRepository;
import com.m2l.m2l.repositories.BookRepository;
import com.m2l.m2l.repositories.EditorRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private EditorRepository editorRepository;
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Article> findAll() {
		
		return articleRepository.findAll();
	}

	@Override
	public Boolean remove(int id) {
		Article article = articleRepository.findById(id).orElse(null);
		if (article != null) {
			articleRepository.delete(article);
			return true;
		}
		return false;
	}

	@Override
	public Article update(@Valid CreateArticle request) {
		try {
			Editor editor = editorRepository.findById(request.getEditor()).orElse(null);
			Book book = bookRepository.findById(request.getBook()).orElse(null);
			Article article = Article.builder()
					.id(request.getId())
					.title(request.getTitle())
					.width(request.getWidth())
					.height(request.getHeight())
					.thickness(request.getThickness())
					.number_isbn(request.getNumber_isbn())
					.price(request.getPrice())
					.stock(request.getStock())
					.editor(editor)
					.book(book)
					.build();
			return articleRepository.save(article);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Page<Article> findActiveArticles(int offset, int pageSize) {
		Page<Article> articles = articleRepository.findByActiveOrderByAddDateDesc(true,
				PageRequest.of(offset, pageSize));
		return articles;
	}

	@Override
	public Page<Article> findAllArticles(int offset) {
		Page<Article> articles = articleRepository.findAllByAddDate(PageRequest.of(offset, 9));
		return articles;
	}

	@Override
	public Page<Article> searchArticles(String title, int offset, int pageSize) {
		try {
			String urlSearch = URLDecoder.decode(title, StandardCharsets.UTF_8.name());
			Page<Article> articles = articleRepository.searchActiveAndBooksByTitleOrEditorOrStyleOrAuthors(true,
					urlSearch, PageRequest.of(offset, pageSize));
			return articles;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	@Override
	public Article findById(int id) {
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public CreateArticle findByIdCreate(int id) {
		Article article = articleRepository.findById(id).orElse(null);
		if (article == null) {
			return null;
		}
		CreateArticle createArticle = new CreateArticle();
		createArticle.setId(article.getId());
		createArticle.setTitle(article.getTitle());
		createArticle.setWidth(article.getWidth());
		createArticle.setHeight(article.getHeight());
		createArticle.setThickness(article.getThickness());
		createArticle.setStock(article.getStock());
		createArticle.setPrice(article.getPrice());
		createArticle.setNumber_isbn(article.getNumber_isbn());
		createArticle.setEditor(article.getEditor().getId());
		createArticle.setBook(article.getBook().getId());
		return createArticle;
	}

	@Override
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	@Override
	public Article createArticle(@RequestBody CreateArticle request) {
		try {
			Book book = bookRepository.findById(request.getBook()).orElse(null);
			Editor editor = editorRepository.findById(request.getEditor()).orElse(null);
			if (book == null) {
				throw new RuntimeException("Aucun livre n'a été trouvé avec l'id du livre");
			}
			if (editor == null) {
				throw new RuntimeException("Aucun éditeur n'a été trouvé avec l'id de l'éditeur");
			}
			Article article = Article.builder()
					.title(request.getTitle())
					.width(request.getWidth())
					.height(request.getHeight())
					.thickness(request.getThickness())
					.number_isbn(request.getNumber_isbn())
					.price(request.getPrice())
					.stock(request.getStock())
					.editor(editor)
					.book(book)
					.build();
			return articleRepository.save(article);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Article> saveAll(List<Article> articles) {
		return articleRepository.saveAll(articles);
	}
}