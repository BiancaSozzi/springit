package com.spring.course.springit.controller;

import com.spring.course.springit.domain.Link;
import com.spring.course.springit.repository.LinkRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/links")
public class LinkController {

	private final LinkRepository linkRepository;

	public LinkController(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	//list
	@RequestMapping("/")
	public List<Link> list() {
		return linkRepository.findAll();
	}

	//CRUD
	@PostMapping("/create")

	/*
		the @ModelAttribute will take a query string. so, all the data are being pass to the server through the url.
		As for @RequestBody, all the data will be pass to the server through a full JSON body.
	 */
	public Link create(@ModelAttribute Link link) {
		return linkRepository.save(link);
	}

	@GetMapping("/{linkId}")
	public Optional<Link> read(@PathVariable Long linkId) {
		return linkRepository.findById(linkId);
	}

	@PutMapping("/{linkId}")
	public Link update(@PathVariable Long linkId, @ModelAttribute Link link) {
		if (link.getId().equals(linkId)){
			return linkRepository.save(link);
		}
		return null;
	}

	@DeleteMapping("/{linkId}")
	public void delete(@PathVariable Long linkId) {
		linkRepository.deleteById(linkId);
	}

}
