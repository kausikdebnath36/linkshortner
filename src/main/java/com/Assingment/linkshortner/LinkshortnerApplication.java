package com.Assingment.linkshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;


@SpringBootApplication
public class LinkshortnerApplication {

	public static void main(String[] args) {

		SpringApplication.run(LinkshortnerApplication.class, args);
		LocalDateTime localDateTime = LocalDateTime.now();
		String currentdate =localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(currentdate);
		java.util.Date currentDate2 = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDateTime = dateFormat. format(currentDate2);
		System.out.println(currentDate2);




	}

}
