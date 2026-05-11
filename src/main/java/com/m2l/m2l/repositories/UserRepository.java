package com.m2l.m2l.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.m2l.m2l.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	@Query("""
				SELECT DISTINCT u FROM User u WHERE u.id = :keyword
			""")
	User selectUserById(@Param("keyword") Integer id);
}