package com.charles.crawler.common;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public enum AmazonProductPriceAttribute {

    AMAZON_PRODUCT_PRICE_ATTRIBUTE{
        @Override
        public String toString(){
            return "[class=priceLarge]";
        }
    },

    AMAZON_PRODUCT_ATTR_PRICE_KIT{
        @Override
        public String toString(){
            return "[class=priceLarge kitsunePrice]";
        }
    }
}
