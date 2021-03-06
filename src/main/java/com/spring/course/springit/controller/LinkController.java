package com.spring.course.springit.controller;

import com.spring.course.springit.domain.Comment;
import com.spring.course.springit.domain.Link;
import com.spring.course.springit.service.CommentService;
import com.spring.course.springit.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LinkController {

	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	private final LinkService linkService;
	private final CommentService commentService;

	public LinkController(LinkService linkService, CommentService commentService) {
		this.linkService = linkService;
		this.commentService = commentService;
	}

	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkService.findAll());
		return "link/list";
	}

	@GetMapping("/link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkService.findById(id);
		if (link.isPresent()) {
			Link currentLink = link.get();
			Comment comment = new Comment();
			model.addAttribute("comment", comment);
			model.addAttribute("link", currentLink);
			model.addAttribute("success", model.containsAttribute("success"));
			return "link/view";
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/link/submit")
	public String newLinkForm(Model model){
		model.addAttribute("link", new Link());
		return "link/submit";
	}

	@PostMapping("/link/submit")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		// @Valid --> Link object that is expected
		// BindingResult, to figure out if it was valid or not
		if( bindingResult.hasErrors() ) {
			logger.info("Validation errors were found while submitting a new link.");
			model.addAttribute("link",link);
			return "link/submit";
		} else {
			// save our link
			linkService.save(link);
			logger.info("New link was saved successfully");
			redirectAttributes
					.addAttribute("id",link.getId())
					.addFlashAttribute("success",true);

			// flashAttributes --> only lives in the next template. If you reload that page is no longer there.
			return "redirect:/link/{id}";
		}
	}

	@Secured({"ROLE_USER"})
	@PostMapping("/link/{id}/comments")
	public String addComment(@PathVariable Long id, @Valid Comment comment, BindingResult bindingResult) {
		Optional<Link> link = linkService.findById(id);
		if (link.isPresent()) {
			Link theLink = link.get();
			if (bindingResult.hasErrors()) {
				logger.info("There was a problem adding a new comment.");
			} else {
				Comment myComment = new Comment();
				myComment.setLink(theLink);
				myComment.setBody(comment.getBody());
				commentService.save(myComment);
				theLink.addComment(myComment);
				logger.info("New comment was saved successfully.");
			}
		}
		return "redirect:/link/" + id;
	}


}
