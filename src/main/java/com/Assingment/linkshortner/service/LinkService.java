package com.Assingment.linkshortner.service;

import com.Assingment.linkshortner.dto.LinkDetailDto;

import java.util.Date;
import java.util.List;

public interface LinkService {

    LinkDetailDto createShortlink(LinkDetailDto link);

    LinkDetailDto getDetailByShortUrl(String link);

    List<LinkDetailDto> getAllShortUrl();

    LinkDetailDto updateShortUrl(List<LinkDetailDto> list,String shortUrl,String newOriginalUrl);


}
