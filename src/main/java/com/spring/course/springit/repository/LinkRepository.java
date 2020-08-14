package com.spring.course.springit.repository;

import com.spring.course.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LinkRepository extends JpaRepository<Link, Long> {

	// JpaRepository<T, ID> --> <Entity class, data type of id>

//	@Query(value="SELECT * FROM Link where title = ?1 ", nativeQuery = true)
//	Link findByTitle( String title);


}
