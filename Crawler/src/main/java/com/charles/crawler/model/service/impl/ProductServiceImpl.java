package com.charles.crawler.model.service.impl;

import com.charles.crawler.model.dao.interfaces.ProductDAO;
import com.charles.crawler.model.entity.PageLinkProductElement;
import com.charles.crawler.model.entity.Product;
import com.charles.crawler.model.service.interfaces.ProductService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    private LinkedList<Product> products;

    public List<Product> getAllProducts() {
        return getProductDAO().selectAll();
    }

    public void insertAll(List<Product> products) {
        getProductDAO().cartUpdates(products);
    }

    public void addProduct(Product product) {
        getProductDAO().insert(product);
    }

    public Product getProductById(String id) {

        return getProductDAO().findById(id);
    }

    public void addProductByElement(PageLinkProductElement productElement) {
        Product product = new Product();
        product.setId(productElement.getId());
        product.setAbsoluteUrl(product.getAbsoluteUrl());
        product.setDescription(productElement.getDescription());
        product.setPrice(productElement.getPrice());
        product.setTitle(productElement.getTitle());
        getNotInsertedProducts().add(product);
    }

    private LinkedList<Product> getNotInsertedProducts() {
        return products;
    }

    public void printProductToCsvFileByDefault() {

    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<Product> products) {
        this.products = products;
    }
}
