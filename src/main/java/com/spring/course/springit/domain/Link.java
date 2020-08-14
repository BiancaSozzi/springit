package com.spring.course.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data // Gives you the @RequiredArgsConstructor and getter /setter
@ToString
public class Link {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String url;

	// comments
	@OneToMany(mappedBy = "link")
	private List<Comment> comments = new ArrayList<>();

}
