package com.Assingment.linkshortner.service.impl;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.entity.LinkDetail;
import com.Assingment.linkshortner.mapper.LinkDetailMapper;
import com.Assingment.linkshortner.repository.LinkDetailRepository;
import com.Assingment.linkshortner.service.LinkService;
import com.Assingment.linkshortner.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class LinkServiceImpl implements LinkService {

private final RandomStringGenerator randomStringGenerator;

private final LinkDetailRepository linkDetailRepository;
    @Autowired
    public LinkServiceImpl(RandomStringGenerator randomStringGenerator, LinkDetailRepository linkDetailRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.linkDetailRepository = linkDetailRepository;
    }

    @Override
    public LinkDetailDto createShortlink(LinkDetailDto link) {
         if(link.getShortUrl()==null||link.getShortUrl().isEmpty()){
            link.setShortUrl(generaterandomstring(link.getId()));
            if(link.getCreatedAt()==null){
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTime = dateFormat. format(currentDate);

                link.setCreatedAt(currentDate);
                link.setUpdatedAt(currentDate);
                link.setExpiryDate(currentDate);
            }
         }
         else {
                throw new RuntimeException("Short Link already exist.That is "+link.getShortUrl());
         }
        LinkDetail linkDetail= LinkDetailMapper.maptoLinkDetail(link);
        LinkDetail saved=linkDetailRepository.save(linkDetail);
        return LinkDetailMapper.maptoLinkDetailDto(saved);
    }

    @Override
    public LinkDetailDto getDetailByShortUrl(LinkDetailDto link) {
        return null;
    }

    @Override
    public LinkDetailDto updateExpiryDate(LinkDetailDto link) {
        return null;
    }

//    @Override
//    public void deleteUrl() {
//
//    }

    private String generaterandomstring(Long i) {
        String randomurl;
        do {
            randomurl=randomStringGenerator.generateRandomString();
        }
        while(linkDetailRepository.findById(i).isPresent());
            return randomurl;
    }
}
