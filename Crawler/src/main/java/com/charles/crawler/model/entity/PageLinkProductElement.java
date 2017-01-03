package com.charles.crawler.model.entity;

import java.security.BasicPermission;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public class PageLinkProductElement extends BasicPageLinkElement{
    String title = null;
    String description = null;
    Double price = null;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                "Class: PageLinkProductElement ID {").append(this.getId())
                .append("}\r\n").append("Title [").append(this.getTitle())
                .append("] Price : [").append(this.getPrice())
                .append("] Description : [").append(this.getDescription())
                .append("]\r\n").append("URL [")
                .append(this.getAbsoluteHrefValue()).append("]");
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
