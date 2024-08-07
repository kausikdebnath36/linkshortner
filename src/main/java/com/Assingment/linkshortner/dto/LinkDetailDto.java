package com.Assingment.linkshortner.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDetailDto {

	private Long id;
	private String shortUrl;
	private String originalUrl;
	private Date expiryDate;
	private Date createdAt;
	private Date updatedAt;
}
