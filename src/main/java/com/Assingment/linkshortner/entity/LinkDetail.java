package com.Assingment.linkshortner.entity;

//import java.sql.Date;

import java.util.Date;

import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema="linkshortner",name="tbltLinkDetail")
@Entity
public class LinkDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="short_url")
	private String shortUrl;
	@Column(name="original_url")
	private String originalUrl;
	@Column(name="expiry_date")
	private Date expiryDate;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updated_at")
	private Date updatedAt;
	
	
	

}
