package com.Assingment.linkshortner.controller;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.entity.LinkDetail;
import com.Assingment.linkshortner.mapper.LinkDetailMapper;
import com.Assingment.linkshortner.service.LinkService;
import com.Assingment.linkshortner.util.ResposeObj;
import com.Assingment.linkshortner.util.Status;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class LinkShortnerController {

 private LinkService linkService;

    public LinkShortnerController(LinkService linkService) {

        this.linkService = linkService;
    }
    //create new short link
    @PostMapping
    public ResposeObj createShortLink(@RequestBody LinkDetailDto linkDetailDto){

        ResposeObj resposeObj;
        LinkDetailDto linkDetailDto1;
        try {
             linkDetailDto1=linkService.createShortlink(linkDetailDto);
        }
        catch (Exception exception){
            return new ResposeObj(Status.FAILURE);
        }
        resposeObj= new ResposeObj(linkDetailDto1.getShortUrl(),linkDetailDto1.getId(),Status.SUCCESS);

        return resposeObj;
    }

    //Modifi
    @GetMapping("/{shortUrl}")
    public ResponseEntity<LinkDetailDto> redirect( @PathVariable String shortUrl) throws URISyntaxException {

        LinkDetailDto shortdetail = linkService.getDetailByShortUrl(shortUrl);

        URI uri = new URI(shortdetail.getOriginalUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders,HttpStatus.SEE_OTHER);
    }

    @PutMapping
    public ResposeObj updateShortLink( @RequestBody Map<String,String> updateAt, Map<String,String> updateWith){
        String shortUrl= updateAt.get("shortUrl");
        String originalUrl=updateWith.get("originalUrl");
        LinkDetailDto linkDetailDto1=linkService.updateShortUrl(linkService.getAllShortUrl(),shortUrl,originalUrl);
        return new ResposeObj(linkDetailDto1.getShortUrl(),linkDetailDto1.getId(),Status.SUCCESS);
    }

    @GetMapping
    public ResponseEntity<List<LinkDetailDto>> getAllUrls(){
        List<LinkDetailDto> accounts= linkService.getAllShortUrl();
        return ResponseEntity.ok(accounts);
    }


}
