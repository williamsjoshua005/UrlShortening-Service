package com.interswitch.assessment.urlshortening.model;

import java.io.Serializable;

public class Url implements Serializable {
    private String shortenUrl;
    private String longUrl;
    private String key;

    public String getShortenUrl() {
        return shortenUrl;
    }
    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getLongUrl() {
        return longUrl;
    }
    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
    public Url() {

    }
    public Url(String shortenUrl, String longUrl) {
        this.shortenUrl = shortenUrl;
        this.longUrl=longUrl;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Url [shortenUrl=");
        builder.append(shortenUrl);
        builder.append(", longUrl=");
        builder.append(longUrl);
        builder.append(", key=");
        builder.append(key);
        builder.append("]");
        return builder.toString();
    }
}