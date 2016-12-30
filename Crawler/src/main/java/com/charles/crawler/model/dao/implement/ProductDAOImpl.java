package com.charles.crawler.model.dao.implement;

import com.charles.crawler.model.dao.interfaces.ProductDAO;
import com.charles.crawler.model.dao.rowmapper.ProductRowMapper;
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

        log.info("->-> Ending method ProductDAOImpl.insert()");

        return updateCounts;
    }

    public void insert(Product product) {
        log.debug("->-> Starting method ProductDAOImpl.insert()");

        String insertSql = "INSERT INTO PRODUCT (URL, TITLE, PRICE, DESCRIPTION) VALUES(?,?,?,?);";

        getJdbcTemplate().update(insertSql, new Object[]{product.getUrl(), product.getTitle(),
                                                         product.getPrice(), product.getDescription()});
        log.info("->-> Ending method ProductDAOImpl.insert()");
    }

    @SuppressWarnings("unchecked")

    public List<Product> selectAll() {
        log.debug("->-> Starting method ProductDAOImpl.selectAll()");

        String selectAllSql = "SELECT * FROM PRODUCT;";

        List<Product> productLists = getJdbcTemplate().query(selectAllSql, new ProductRowMapper());

        log.info("->-> Ending method ProductDAOImpl.selectAll()");

        return productLists;
    }

    public Product findById(String id) {
        String findByIdSql = "SELECT * FROM PRODUCT WHERE TITLE =?;";

        Product product = (Product) jdbcTemplate.queryForObject(findByIdSql, new Object[]{id}, new ProductRowMapper());

        return product;
    }
}
