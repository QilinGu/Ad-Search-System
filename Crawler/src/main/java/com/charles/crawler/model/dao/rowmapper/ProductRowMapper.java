package com.charles.crawler.model.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ChenCheng on 12/30/2016.
 */
public class ProductRowMapper implements RowMapper{
    String title = null;
    String description = null;
    String url = null;
    Double price = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductRowMapper product =  new ProductRowMapper();

        product.setDescription(resultSet.getString("DESCRIPTION"));

        product.setPrice(resultSet.getDouble("PRICE"));

        product.setTitle(resultSet.getString("TITLE"));

        product.setUrl(resultSet.getString("URL"));

        return product;
    }
}
