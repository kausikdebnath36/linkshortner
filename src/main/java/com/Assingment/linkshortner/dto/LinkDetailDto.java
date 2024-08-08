package com.Assingment.linkshortner.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LinkDetailDto {

	private Long id;
	private String shortUrl;
	private String originalUrl;
	private Date expiryDate;
	private Date createdAt;
	private Date updatedAt;
	private int addOnDays;

	public LinkDetailDto(Long id, String shortUrl, String originalUrl, Date expiryDate, Date createdAt, Date updatedAt) {
		this.id = id;
		this.shortUrl = shortUrl;
		this.originalUrl = originalUrl;
		this.expiryDate = expiryDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
