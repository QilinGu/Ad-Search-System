package com.charles.crawler.common.interfaces;

import com.charles.crawler.model.entity.BasicPageLinkElement;

import java.util.List;

import com.charles.crawler.model.entity.PageLinkProductElement;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public interface PagePageAnalyzer {

    List<BasicPageLinkElement> readPage(String url) throws Exception;

    GenericCategoriesType getCategoryType(String url);

    String getCategoryId(String url, GenericCategoriesType categoryType);

    void addNewCategory(List<BasicPageLinkElement> elements, String url, String categoryId);

    GenericProductsType getProductType(String url);

    String getProductId(String url, GenericProductsType productType);

    void addNewProduct(List<BasicPageLinkElement> elements, String url, String productId);

    PageLinkProductElement populateProductElementsAttributes(PageLinkProductElement plpe);

    boolean isExcludedProduct(PageLinkProductElement plpe);

}
