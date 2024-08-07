package com.Assingment.linkshortner.util;

import com.Assingment.linkshortner.dto.LinkDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
@Data
public class ResposeObj {
    private String shortUrl;

    private Long id;

    private Status status;

    public ResposeObj(Status status) {
        this.status = status;
    }

    public ResposeObj() {//
//        default constructor
    }

    public ResposeObj(String shortUrl, Long id, Status status) {
        this.shortUrl = shortUrl;
        this.id=id;
        this.status = status;
    }


}
