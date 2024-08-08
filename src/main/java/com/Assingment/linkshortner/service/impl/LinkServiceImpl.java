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
             link.setShortUrl(generaterandomstring());
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
        return linkDetailRepository.findByShortUrl(ShortUrl).orElseThrow(()->new RuntimeException("Url not found"));
    }

    @Override
    public List<LinkDetailDto> getAllShortUrl() {
       List<LinkDetail> lists=linkDetailRepository.findAll();
       return lists.stream().map((list)->LinkDetailMapper.maptoLinkDetailDto(list)).collect(Collectors.toList());
    }

    @Override
    public LinkDetailDto updateShortUrl(List<LinkDetailDto> list,String shortUrl, String newOriginalUrl) {
//        LinkDetailDto linkDetailDto=linkDetailRepository.findByShortUrl(shortUrl).orElseThrow(()-> new RuntimeException("Short url does not exist"));
//        LinkDetail linkDetail=linkDetailRepository.findById(linkDetailDto.getId()).orElseThrow(()-> new RuntimeException("no such short url"));
//        Long id=null;
//        if(compareShortUrlFromList(linkDetailDto,list)){
//             LinkDetailDto List=new LinkDetailDto();
//             List= findList(linkDetailDto);
//           id=List.getId();
//         }
//        LinkDetail linkDetail=linkDetailRepository.findById(id).orElseThrow(()-> new RuntimeException("no such short url"));
//        linkDetail.setOriginalUrl(newOriginalUrl);
//        LinkDetail saved=linkDetailRepository.save(linkDetail);
//        return LinkDetailMapper.maptoLinkDetailDto(saved);
 return null;
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
    private LinkDetailDto findList(LinkDetailDto link){
        List<LinkDetailDto> temp= getAllShortUrl();
        ResposeObj resposeObj;
        Long id=0L;
        for(LinkDetailDto obj:temp) {

            if(link.getOriginalUrl().equals(obj.getOriginalUrl())) {

                return  new LinkDetailDto(obj.getId(), obj.getShortUrl(), obj.getOriginalUrl(),obj.getExpiryDate(),obj.getCreatedAt(),obj.getUpdatedAt());

            }
        }
        return null;
    }

}
