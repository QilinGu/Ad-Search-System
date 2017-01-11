package com.charles.crawler.common;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public enum NotFoundMessage {
    PRODUCT_TITLE_NOT_FOUND{
        @Override
        public String toString(){
            return "Title not Found !!!";
        }
    },

    PRODUCT_Description_NOT_FOUND{
        @Override
        public String toString(){
            return "Description not Found!!!";
        }
    }
}
