package com.m2l.m2l.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.m2l.m2l.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	@Query("""
			SELECT DISTINCT id,CONCAT(firstname, ' ', lastname) FROM Author a
			""")
	Page<Author> selectNameAuthor(Pageable pageable);

	@Query("""
			SELECT DISTINCT id, CONCAT(firstname, ' ', lastname) FROM Author a WHERE
			LOWER(CONCAT(a.lastname, ' ', a.firstname)) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(CONCAT(a.firstname, ' ', a.lastname)) LIKE LOWER(CONCAT('%', :keyword,'%'))
			""")
	Page<Author> selectAuthorByTitle(@Param("keyword") String keyword, Pageable pageable);
}