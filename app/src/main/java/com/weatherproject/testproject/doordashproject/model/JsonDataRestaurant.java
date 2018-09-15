
package com.weatherproject.testproject.doordashproject.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonDataRestaurant {

    private String status;
    private Object asapTime;
    private String description;
    private Business business;
    private List<String> tags = null;
    private String cover_img_url;
    private String name;
    private String url;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAsapTime() {
        return asapTime;
    }

    public void setAsapTime(Object asapTime) {
        this.asapTime = asapTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }



    public String getCover_img_url () {
        return cover_img_url;
    }

    public void setCover_img_url (String cover_img_url) {
        this.cover_img_url = cover_img_url;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
