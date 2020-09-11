package com.spring.course.springit.controller;

import com.spring.course.springit.domain.Link;
import com.spring.course.springit.domain.Vote;
import com.spring.course.springit.service.LinkService;
import com.spring.course.springit.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

	private final VoteService voteService;
	private final LinkService linkService;

	public VoteController(VoteService voteService, LinkService linkService) {
		this.voteService = voteService;
		this.linkService = linkService;
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable long linkID, @PathVariable short direction, @PathVariable int voteCount) {
		Optional<Link> fetchedLink = linkService.findById(linkID);
		if (fetchedLink.isPresent()){
			Link link = fetchedLink.get();
			Vote vote = new Vote(direction, link);
			voteService.save(vote);
			int updatedVoteCount = voteCount + direction;
			link.setVoteCount(updatedVoteCount);
			linkService.save(link);
			return updatedVoteCount;
		}
		// if link not found return voteCount
		return voteCount;
	}
}
