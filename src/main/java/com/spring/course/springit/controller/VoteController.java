package com.spring.course.springit.controller;

import com.spring.course.springit.domain.Link;
import com.spring.course.springit.domain.Vote;
import com.spring.course.springit.repository.LinkRepository;
import com.spring.course.springit.repository.VoteRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

	private VoteRepository voteRepository;
	private LinkRepository linkRepository;

	public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
		this.voteRepository = voteRepository;
		this.linkRepository = linkRepository;
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable long linkID, @PathVariable short direction, @PathVariable int voteCount) {
		Optional<Link> fetchedLink = linkRepository.findById(linkID);
		if (fetchedLink.isPresent()){
			Link link = fetchedLink.get();
			Vote vote = new Vote(direction, link);
			voteRepository.save(vote);
			int updatedVoteCount = voteCount + direction;
			link.setVoteCount(updatedVoteCount);
			linkRepository.save(link);
			return updatedVoteCount;
		}
		// if link not found return voteCount
		return voteCount;
	}
}
