package com.Assingment.linkshortner.controller;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.service.LinkService;
import com.Assingment.linkshortner.util.ResponseObjBol;
import com.Assingment.linkshortner.util.ResposeObj;
import com.Assingment.linkshortner.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping
public class LinkShortnerController {

 private LinkService linkService;
    @Autowired
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
         new ResposeObj(linkDetailDto1.getShortUrl(),linkDetailDto1.getId(),Status.SUCCESS);

        return new ResposeObj(linkDetailDto1.getShortUrl(),linkDetailDto1.getId(),Status.SUCCESS);
    }

    //Redirect api
    @GetMapping("/{shortUrl}")
    public ResponseEntity<LinkDetailDto> redirect( @PathVariable String shortUrl) throws URISyntaxException {

        shortUrl="http://localhost:8080/"+shortUrl;
        LinkDetailDto shortdetail = linkService.getDetailByShortUrl(shortUrl);

            URI uri = new URI(shortdetail.getOriginalUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(httpHeaders,HttpStatus.SEE_OTHER);


    }

    @PostMapping("update")
    public ResponseObjBol updateShortLink(@RequestBody LinkDetailDto linkDetailDto){

        try{
            linkService.updateShortUrl(linkDetailDto.getShortUrl(),linkDetailDto.getOriginalUrl());
        }catch(Exception e){
            return new ResponseObjBol(false);
        }

        return new ResponseObjBol(true);
    }


    //get all short Api
    @GetMapping
    public ResponseEntity<List<LinkDetailDto>> getAllUrls(){
        List<LinkDetailDto> accounts= linkService.getAllShortUrl();
        return ResponseEntity.ok(accounts);
    }
    @PostMapping("updateExpiry")
    public ResponseObjBol updateExpiryDate(@RequestBody LinkDetailDto linkDetailDto){

        try {
            linkService.updateExpiry(linkDetailDto.getShortUrl(),linkDetailDto.getAddOnDays());
        } catch (Exception e){

            return new ResponseObjBol(false);
        }
        return new ResponseObjBol(true);
    }


}
