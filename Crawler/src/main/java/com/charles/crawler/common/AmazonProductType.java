package com.charles.crawler.common;

import com.charles.crawler.common.interfaces.GenericProductsType;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public enum AmazonProductType implements GenericProductsType{

    AMAZON_PRODUCT_TYPE_GP {
        @Override
        public String toString() {
            return "/gp/product/";
        }

        @Override
        public String[] getRelativeURLAndProductID(String fullProductURL,
                                                   GenericProductsType productType) {
            String productID = null;
            String productTruncateRelativeURL = null;

            // TODO Need a Helper/Utils method or a regular expression to avoid
            // this multiple if
            int index = fullProductURL.indexOf(AMAZON_PRODUCT_TYPE_GP
                    .toString());
            if (index != -1) {
                int indexProductIDEnd = fullProductURL.indexOf('?', index
                        + AMAZON_PRODUCT_TYPE_GP.toString().length());
                if (indexProductIDEnd == -1) {
                    indexProductIDEnd = fullProductURL.indexOf('/', index
                            + AMAZON_PRODUCT_TYPE_GP.toString().length());
                    if (indexProductIDEnd == -1) {
                        indexProductIDEnd = fullProductURL.indexOf('&', index
                                + AMAZON_PRODUCT_TYPE_GP.toString().length());
                        // If no token ? / & has been found
                        if (indexProductIDEnd == -1) {
                            indexProductIDEnd = fullProductURL.length();
                        }

                    }
                }

                productID = fullProductURL.substring(index
                                + AMAZON_PRODUCT_TYPE_GP.toString().length(),
                        indexProductIDEnd);
                productTruncateRelativeURL = fullProductURL.substring(0,
                        indexProductIDEnd);
            }
            ;
            String[] productRelativeURLAndID = new String[2];
            productRelativeURLAndID[0] = productID;
            productRelativeURLAndID[1] = productTruncateRelativeURL;
            return productRelativeURLAndID;
        }
    },
    AMAZON_PRODUCT_TYPE_DP {
        @Override
        public String toString() {
            return "/dp/";
        }

        @Override
        public String[] getRelativeURLAndProductID(String fullProductURL,
                                                   GenericProductsType productType) {
            String productID = null;
            String productTruncateRelativeURL = null;
            // TODO Need a Helper/Utils method or a regular expression to avoid
            // this multiple if
            int index = fullProductURL.indexOf(AMAZON_PRODUCT_TYPE_DP
                    .toString());
            if (index != -1) {
                int indexProductIDEnd = fullProductURL.indexOf('?', index
                        + AMAZON_PRODUCT_TYPE_DP.toString().length());
                if (indexProductIDEnd == -1) {
                    if (indexProductIDEnd == -1) {
                        indexProductIDEnd = fullProductURL.indexOf('/', index
                                + AMAZON_PRODUCT_TYPE_DP.toString().length());
                        if (indexProductIDEnd == -1) {
                            indexProductIDEnd = fullProductURL.indexOf('&',
                                    index
                                            + AMAZON_PRODUCT_TYPE_DP.toString()
                                            .length());
                            // If no token ? / & has been found
                            if (indexProductIDEnd == -1) {
                                indexProductIDEnd = fullProductURL.length();
                            }

                        }
                    }
                }
                productID = fullProductURL.substring(index
                                + AMAZON_PRODUCT_TYPE_DP.toString().length(),
                        indexProductIDEnd);
                productTruncateRelativeURL = fullProductURL.substring(0,
                        indexProductIDEnd);
            }
            String[] productRelativeURLAndID = new String[2];
            productRelativeURLAndID[0] = productID;
            productRelativeURLAndID[1] = productTruncateRelativeURL;
            return productRelativeURLAndID;
        }
    };
}
