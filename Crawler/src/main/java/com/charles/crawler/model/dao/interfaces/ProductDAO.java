package com.charles.crawler.model.dao.interfaces;

import com.charles.crawler.model.entity.Product;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by ChenCheng on 12/29/2016.
 */
public interface ProductDAO {

    Log log = LogFactory.getLog(ProductDAO.class);

    int[] cartUpdates(final List<Product> products);

    void insert(Product product);

    List<Product> selectAll();

    Product findById(String id);
}
