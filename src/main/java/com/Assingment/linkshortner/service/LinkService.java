package com.Assingment.linkshortner.service;

import com.Assingment.linkshortner.dto.LinkDetailDto;

import java.util.Date;

public interface LinkService {

    LinkDetailDto createShortlink(LinkDetailDto link);

    LinkDetailDto getDetailByShortUrl(LinkDetailDto link);

    LinkDetailDto updateExpiryDate(LinkDetailDto link);

//    LinkDetailDto getAllShortUrl(LinkDetailDto link);

//    void deleteUrl(Date expiry);
}
