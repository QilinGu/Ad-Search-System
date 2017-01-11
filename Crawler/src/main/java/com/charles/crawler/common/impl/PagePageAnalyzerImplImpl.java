package com.charles.crawler.common.impl;

import com.charles.crawler.common.*;
import com.charles.crawler.common.interfaces.GenericCategoriesType;
import com.charles.crawler.common.interfaces.GenericProductsType;
import com.charles.crawler.util.ParseUtils;
import org.apache.commons.lang3.EnumUtils;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.Set;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public class PagePageAnalyzerImplImpl extends PagePageAnalyzerImpl {
    public PagePageAnalyzerImplImpl() {
    }

    @Override
    protected Double getProductPrice(Document doc, String id) {
        String productPrice = getAttributeValueFromLinkHtml(doc, id, AmazonProductPriceAttribute.class);

        return ParseUtils.parsePrice(productPrice);
    }

    @Override
    protected String getProductTitle(Document doc, String id) {
        String productTitle = getAttributeValueFromLinkText(doc, id, AmazonProductTitleAttribute.class);
        return ((productTitle == null) ? NotFoundMessage.PRODUCT_TITLE_NOT_FOUND.toString() : productTitle);
    }

    @Override
    protected String getProductDescription(Document doc, String id) {
        String productDescription = getAttributeValueFromLinkText(doc, id, AmazonProductDescriptionAttribute.class);
        if (productDescription == null) {
            productDescription = getAttributeValueFromMetaContentByName(doc, "description", "content");
        }

        return ((productDescription == null) ? NotFoundMessage.PRODUCT_Description_NOT_FOUND.toString() : productDescription);
    }

    @Override
    public String getCategoryId(String url, GenericCategoriesType categoriesType) {
        return categoriesType.getCategoryByDefinedTokens(url, categoriesType);
    }

    public String[] getRelativeURLAndProductId(String url, GenericProductsType productsType) {
        return productsType.getRelativeURLAndProductID(url, productsType);
    }

    @Override
    public GenericCategoriesType getCategoryType(String url) {
        for (AmazonCategoriesType categoriesType : EnumUtils.getEnumList(AmazonCategoriesType.class)) {
            if (url.contains(categoriesType.toString())) {
                return categoriesType;
            }
        }
        return null;
    }

    @Override
    public GenericProductsType getProductType(String url) {
        for (AmazonProductType productType : EnumUtils.getEnumList(AmazonProductType.class)) {
            if (url.contains(productType.toString())) {
                return productType;
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Class getExcludedProductClass() {

        return AmazonExcludedProductType.class;
    }

    public PagePageAnalyzerImplImpl(Set<String> alreadyAddedCategorySet, Set<String> alreadyAddedProductSet, HTMLParser htmlParser) throws IOException {
        super(alreadyAddedCategorySet, alreadyAddedProductSet, htmlParser);
    }

    @Override
    public String getProductId(String url, GenericProductsType productType) {
        return productType.getRelativeURLAndProductID(url, productType)[0];
    }
}