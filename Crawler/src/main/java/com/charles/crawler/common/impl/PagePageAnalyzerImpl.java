package com.charles.crawler.common.impl;

import com.charles.crawler.common.interfaces.GenericCategoriesType;
import com.charles.crawler.common.interfaces.GenericProductsType;
import com.charles.crawler.common.interfaces.PagePageAnalyzer;
import com.charles.crawler.model.entity.BasicPageLinkElement;
import com.charles.crawler.model.entity.PageLinkProductElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by ChenCheng on 1/10/2017.
 */
public abstract class PagePageAnalyzerImpl implements PagePageAnalyzer {
    public PagePageAnalyzerImpl(){}

    private final Log log = LogFactory.getLog(PagePageAnalyzerImpl.class);
    private HTMLParser htmlParser = null;
    public List<BasicPageLinkElement> readPage(String url) throws Exception {
        return null;
    }

    public GenericCategoriesType getCategoryType(String url) {
        return null;
    }

    public String getCategoryId(String url, GenericCategoriesType categoryType) {
        return null;
    }

    public void addNewCategory(List<BasicPageLinkElement> elements, String url, String categoryId) {

    }

    public GenericProductsType getProductType(String url) {
        return null;
    }

    public String getProductId(String url, GenericProductsType productType) {
        return null;
    }

    public void addNewProduct(List<BasicPageLinkElement> elements, String url, String productId) {

    }

    public PageLinkProductElement populateProductElementsAttributes(PageLinkProductElement plpe) {
        return null;
    }

    public boolean isExcludedProduct(PageLinkProductElement plpe) {
        return false;
    }
}
