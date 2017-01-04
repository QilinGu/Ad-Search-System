package com.charles.crawler.common.interfaces;

/**
 * Created by ChenCheng on 1/4/2017.
 */
public interface GenericProductsType {
    String[] getRelativeURLAndProductID(String fullProductURL, GenericProductsType productType);
}
