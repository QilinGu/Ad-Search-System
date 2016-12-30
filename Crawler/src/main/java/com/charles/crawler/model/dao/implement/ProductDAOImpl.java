package com.charles.crawler.model.dao.implement;

import com.charles.crawler.model.dao.interfaces.ProductDAO;
import com.charles.crawler.model.entity.Product;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ChenCheng on 12/30/2016.
 */
public class ProductDAOImpl implements ProductDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int[] cartUpdates(final List<Product> products) {
        log.debug("->-> Starting method ProductDAOImpl.insert()");

        String cartUpdateSql = "INSERT INTO PRODUCT (URL, TITLE, PRICE, DESCRIPTION) VALUES(?,?,?,?);";

        int[] updateCounts = getJdbcTemplate().batchUpdate(cartUpdateSql,
                        new BatchPreparedStatementSetter(){

                            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                                preparedStatement.setString(1, products.get(i).getUrl());
                                preparedStatement.setString(2, products.get(i).getTitle());
                                preparedStatement.setDouble(3, products.get(i).getPrice());
                                preparedStatement.setString(4, products.get(i).getDescription());
                            }

                            public int getBatchSize() {
                                return products.size();
                            }
                        });
        return updateCounts;
    }

    public void insert(Product product) {

    }

    public List<Product> selectAll() {
        return null;
    }

    public Product findById(String id) {
        return null;
    }
}
