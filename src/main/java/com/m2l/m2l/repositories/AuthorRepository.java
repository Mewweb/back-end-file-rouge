package com.m2l.m2l.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.m2l.m2l.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	@Query("""
			SELECT DISTINCT CONCAT(firstname, ' ', lastname) FROM Author a
			""")
	Page<Author> test(Pageable pageable);
}
