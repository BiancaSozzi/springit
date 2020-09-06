package com.spring.course.springit.domain;

import com.spring.course.springit.service.BeanUtil;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Link extends Auditable{

	// Read @NonNull/@Notnull vs @NotEmpty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Please enter a title.")
	private String title;

	@NotEmpty(message="Please enter a URL.")
	@URL(message="Please enter a valid URL")
	private String url;

	// comments
	@OneToMany(mappedBy = "link")
	private List<Comment> comments = new ArrayList<>();

	// Votes
	@OneToMany(mappedBy = "link")
	private List<Vote> votes = new ArrayList<>();

	private int voteCount = 0;

	public Link(String title, String url) {
		this.title = title;
		this.url = url;
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public String getDomainName() throws URISyntaxException {
		URI uri = new URI(this.url);
		String domain = uri.getHost();
		if (domain != null){
			return domain.startsWith("www.") ? domain.substring(4) : domain;
		}
		return uri.toString();
	}

	public String getPrettyTime() {
		PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
		return pt.format(convertToDateViaInstant(getCreatedAt()));
	}

	private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
}
