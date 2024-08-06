package com.Assingment.linkshortner.repository;

import com.Assingment.linkshortner.entity.LinkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkDetailRepository extends JpaRepository<LinkDetail,Long> {
}
