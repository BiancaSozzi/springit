package com.spring.course.springit.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class Vote {

	@Id
	@GeneratedValue
	private Long id;
	private int vote;

	// user
	// link
	@OneToOne
	private Link link;

}
