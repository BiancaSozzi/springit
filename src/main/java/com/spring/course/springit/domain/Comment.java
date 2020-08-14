package com.spring.course.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue
	private Long id;
	private String body;

	// Link
	@ManyToOne
	private Link link;

}
