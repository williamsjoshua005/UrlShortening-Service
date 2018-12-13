package com.interswitch.assessment.urlshortening.util;

import com.interswitch.assessment.urlshortening.model.Url;

public interface ShortenUrlRepository {

    void save(Url Url);

    Url findUrl(String key);
}

