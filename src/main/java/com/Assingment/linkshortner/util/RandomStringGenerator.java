package com.Assingment.linkshortner.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RandomStringGenerator {

//    @Value("${length}")
//    private int codeLength;

    public String generateRandomString() {

        SecureRandom random = new SecureRandom();
        String generated = "";

        var letters =  "abcdefghijklmnöprstuvyzqw123456789"
                .toLowerCase()
                .chars()
                .mapToObj(x->(char)x)
                .collect(Collectors.toList());

        Collections.shuffle(letters);

        for (int i = 0; i < 8; i++) {
            generated += letters.get(random.nextInt(letters.size()));
        }
        return generated;

    }
}
