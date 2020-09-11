package com.spring.course.springit.service;

import com.spring.course.springit.domain.Vote;
import com.spring.course.springit.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

	private final VoteRepository voteRepository;

	public VoteService(VoteRepository voteRepository) {
		this.voteRepository = voteRepository;
	}

	public Vote save (Vote vote) {
		return voteRepository.save(vote);
	}
}
