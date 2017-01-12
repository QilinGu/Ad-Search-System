package com.charles.crawler.common;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public enum AmazonProductTitleAttribute {
    AMAZON_PRODUCT_TITLE_ATTRIBUTE{
        @Override
        public String toString(){
            return "[id=btAsinTitle]";
        }
    },
    AMAZON_PRODUCT_TITLE_ATTRIBUTE_INSTANT_VIDEO{
        @Override
        public String toString(){
            return "[class=hd]";
        }
    },
    AMAZON_PRODUCT_ATTR_TITLE_INSTANT_VIDEO_2 {
        @Override
        public String toString() {
            return "[id=aiv-content-title]";
        }
    },
    AMAZON_PRODUCT_ATTR_TITLE_MP3_STORE {
        @Override
        public String toString() {
            return "[class=a-size-large WebstoreAUISmoothFont a-text-bold]";
        }
    },
    AMAZON_PRODUCT_ATTR_TITLE_MP3_STORE_2 {
        @Override
        public String toString() {
            return "[class=a-size-large WebstoreAUISmoothFont]";
        }
    },
    // 'Books' section
    AMAZON_PRODUCT_ATTR_BOOK_TITLE {
        @Override
        public String toString() {
            return "[id=productTitle]";
        }
    };
}
