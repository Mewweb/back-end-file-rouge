package com.m2l.m2l.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.m2l.m2l.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	@Query("""
			SELECT DISTINCT c FROM CartItem c JOIN c.article a JOIN c.user u WHERE u.email = :email
			""")
	List<CartItem> findCartItemByUser(@Param("email") String email);

	@Query("""
			SELECT DISTINCT c FROM CartItem c JOIN c.user u JOIN c.article a WHERE u.email = :email AND a.id = :article
			""")
	CartItem findCartItem(@Param("email") String email, @Param("article") Integer article);
}