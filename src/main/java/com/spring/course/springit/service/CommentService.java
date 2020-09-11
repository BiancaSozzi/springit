package com.spring.course.springit.service;

import com.spring.course.springit.domain.Comment;
import com.spring.course.springit.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}
}
