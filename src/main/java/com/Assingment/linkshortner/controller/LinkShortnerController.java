package com.Assingment.linkshortner.controller;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/linkapi/short")
public class LinkShortnerController {

 private LinkService linkService;

    public LinkShortnerController(LinkService linkService) {

        this.linkService = linkService;
    }
    //create new short link
    @PostMapping
    public ResponseEntity<LinkDetailDto> createShortLink(@RequestBody LinkDetailDto linkDetailDto){
        return new ResponseEntity<>(linkService.createShortlink(linkDetailDto), HttpStatus.CREATED);
    }

    //Modifi
}
