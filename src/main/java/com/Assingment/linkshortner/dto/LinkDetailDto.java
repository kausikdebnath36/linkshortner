package com.Assingment.linkshortner.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LinkDetailDto {

	private Long id;
	
	private String shortUrl;
	
	private String originalUrl;
	
	private Date expiryDate;
	
	private Date createdAt;
	
	private Date updatedAt;
}
