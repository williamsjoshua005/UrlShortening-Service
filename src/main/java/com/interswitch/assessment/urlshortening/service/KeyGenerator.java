package com.interswitch.assessment.urlshortening.service;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

@Service
public class KeyGenerator {
    public String create() {
        RandomStringGenerator randomStringGenerator =
                new RandomStringGenerator.Builder()
                        .withinRange('0', 'z')
                        .filteredBy(CharacterPredicates.ASCII_ALPHA_NUMERALS, CharacterPredicates.DIGITS)
                        .build();
        return randomStringGenerator.generate(7);

    }
    //System.out.println(RandomStringGenerator);
}
