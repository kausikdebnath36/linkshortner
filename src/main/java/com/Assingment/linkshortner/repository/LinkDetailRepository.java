package com.Assingment.linkshortner.repository;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import com.Assingment.linkshortner.entity.LinkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkDetailRepository extends JpaRepository<LinkDetail,Long> {


   Optional <LinkDetail>findByShortUrl(String ShortUrl);


}
