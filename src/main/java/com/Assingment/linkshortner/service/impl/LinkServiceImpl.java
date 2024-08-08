package com.Assingment.linkshortner.service.impl;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.entity.LinkDetail;
import com.Assingment.linkshortner.mapper.LinkDetailMapper;
import com.Assingment.linkshortner.repository.LinkDetailRepository;
import com.Assingment.linkshortner.service.LinkService;
import com.Assingment.linkshortner.util.RandomStringGenerator;
import com.Assingment.linkshortner.util.ResposeObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class LinkServiceImpl implements LinkService {

private final RandomStringGenerator randomStringGenerator;

private final LinkDetailRepository linkDetailRepository;



    public LinkServiceImpl(RandomStringGenerator randomStringGenerator, LinkDetailRepository linkDetailRepository) {
        this.randomStringGenerator = randomStringGenerator;
        this.linkDetailRepository = linkDetailRepository;
    }

    @Override
    public LinkDetailDto createShortlink(LinkDetailDto link) {
         if(compareShortUrlFromList(link,getAllShortUrl())){
             throw new RuntimeException("Link already exist");
         }


         if((link.getShortUrl()==null||link.getShortUrl().isEmpty())){
             link.setShortUrl("http://localhost:8080/"+generaterandomstring());
            if(link.getCreatedAt()==null){
                List<LinkDetailDto> s= getAllShortUrl();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTime = dateFormat. format(currentDate);
                Date creationDate= null;
                try {
                    creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currentDateTime);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                link.setCreatedAt(creationDate);
                link.setUpdatedAt(creationDate);
                link.setExpiryDate(generateExpiryDate(creationDate));
            }
         }
//
        LinkDetail linkDetail= LinkDetailMapper.maptoLinkDetail(link);
        LinkDetail saved=linkDetailRepository.save(linkDetail);
        return LinkDetailMapper.maptoLinkDetailDto(saved);
    }

    @Override
    public LinkDetailDto getDetailByShortUrl(String ShortUrl) {

        return LinkDetailMapper.maptoLinkDetailDto(linkDetailRepository.findByShortUrl(ShortUrl).orElseThrow(()->new RuntimeException("Url not found")));
    }

    @Override
    public List<LinkDetailDto> getAllShortUrl() {
       List<LinkDetail> lists=linkDetailRepository.findAll();
       return lists.stream().map((list)->LinkDetailMapper.maptoLinkDetailDto(list)).collect(Collectors.toList());
    }

    @Override
    public LinkDetailDto updateShortUrl(String shortUrl, String originalUrl) {

       LinkDetail linkDetail=linkDetailRepository.findByShortUrl(shortUrl).orElseThrow(() ->new RuntimeException("short url does not exist in db"));
        linkDetail.setOriginalUrl(originalUrl);
        LinkDetail saved=linkDetailRepository.save(linkDetail);
        return LinkDetailMapper.maptoLinkDetailDto(saved);
    }

    @Override
    public LinkDetailDto updateExpiry(String shortUrl, int addOnDays) {
        LinkDetail linkDetail=linkDetailRepository.findByShortUrl(shortUrl).orElseThrow(() ->new RuntimeException("short url does not exist in db"));
        Date updatedExpiry= new Date(linkDetail.getExpiryDate().getTime()+ TimeUnit.DAYS.toMillis(addOnDays));
        linkDetail.setExpiryDate(updatedExpiry);
        LinkDetail saved=linkDetailRepository.save(linkDetail);
        return LinkDetailMapper.maptoLinkDetailDto(saved);
    }


    private String generaterandomstring() {
        String randomurl;

        randomurl=randomStringGenerator.generateRandomString();

        return randomurl;
    }

    private Date generateExpiryDate(Date creationDate){
//        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 10);
        Date ExpiryDate = cal.getTime();  ;
        return ExpiryDate;

    }
    private Boolean compareShortUrlFromList(LinkDetailDto link,List<LinkDetailDto> temp){
//        = getAllShortUrl();
        ResposeObj resposeObj;
        Long id=0L;
        for(LinkDetailDto obj:temp) {

            if(link.getOriginalUrl().equals(obj.getOriginalUrl())) {

              return  true;

            }
        }
        return false;
    }

    private Long findId(LinkDetailDto link){
        List<LinkDetailDto> temp= getAllShortUrl();
        for(LinkDetailDto obj:temp) {

            if(link.getOriginalUrl().equals(obj.getOriginalUrl())) {

                return obj.getId();

            }
        }
        return null;
    }

}
