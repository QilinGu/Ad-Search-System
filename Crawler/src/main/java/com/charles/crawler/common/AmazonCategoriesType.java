package com.charles.crawler.common;

import com.charles.crawler.common.interfaces.GenericCategoriesType;

import java.security.PublicKey;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public enum AmazonCategoriesType implements GenericCategoriesType{
    AMAZON_CATEGORY_TYPE_NODE{
        @Override
        public String toString(){
            return "node=";
        }

        @Override
        public String getCategoryByDefinedTokens(String fullCategoryURL, GenericCategoriesType categoriesType){
            String category = null;
            int index = fullCategoryURL.indexOf(AMAZON_CATEGORY_TYPE_NODE.toString());
            if (index != -1){
                category = fullCategoryURL.substring(index + AMAZON_CATEGORY_TYPE_NODE.toString().length(), fullCategoryURL.length()-1);

            }

            return category;
        }

    },

    AMAZON_CATEGORIES_TYPE_AMB_LINK{
        @Override
        public String toString(){
            return "amb_link";
        }
        @Override
        public String getCategoryByDefinedTokens(String fullCategoryURL, GenericCategoriesType categoriesType){
            String category = null;
            int index = fullCategoryURL.indexOf(AMAZON_CATEGORIES_TYPE_AMB_LINK.toString());
            if (index != -1){
                int indexQuestionMark = fullCategoryURL.indexOf("?");
                category = fullCategoryURL.substring(index + AMAZON_CATEGORIES_TYPE_AMB_LINK.toString().length(),
                        indexQuestionMark);

            }

            return category;
        }
    },

    AMAZON_CATEGORIES_TYPES_REF_LP{
        @Override
        public String toString(){
            return "s/ref/lp_";
        }
        @Override
        public String getCategoryByDefinedTokens(String fullCategoryURL, GenericCategoriesType categoriesType){
            String category = null;
            int index = fullCategoryURL.indexOf(AMAZON_CATEGORIES_TYPES_REF_LP.toString());
            if (index != -1){
                int indexQuestionMark = fullCategoryURL.indexOf("?");
                category = fullCategoryURL.substring(index + AMAZON_CATEGORIES_TYPES_REF_LP.toString().length(),
                        indexQuestionMark);

            }
            return category;
        }
    };

}
