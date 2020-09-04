package com.spring.course.springit.domain;

import com.spring.course.springit.service.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.ocpsoft.prettytime.PrettyTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private String body;

	// Link
	@NonNull
	@ManyToOne
	private Link link;

	public String getPrettyTime() {
		PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
		return pt.format(convertToDateViaInstant(getCreatedAt()));
	}

	private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}
