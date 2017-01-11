package com.charles.crawler.common.impl;

import com.charles.crawler.common.interfaces.GenericCategoriesType;
import com.charles.crawler.common.interfaces.GenericProductsType;
import com.charles.crawler.common.interfaces.PagePageAnalyzer;
import com.charles.crawler.model.entity.BasicPageLinkElement;
import com.charles.crawler.model.entity.PageLinkCategoryElement;
import com.charles.crawler.model.entity.PageLinkProductElement;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by ChenCheng on 1/10/2017.
 */
public abstract class PagePageAnalyzerImpl implements PagePageAnalyzer {
    public PagePageAnalyzerImpl(){}

    private final Log log = LogFactory.getLog(PagePageAnalyzerImpl.class);
    private HTMLParser htmlParser = null;
    private Set<String> alreadyAddedCategorySet = null;
    private Set<String> alreadyAddedProductSet = null;

    public HTMLParser getHtmlParser() {
        return htmlParser;
    }

    public void setHtmlParser(HTMLParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    public Set<String> getAlreadyAddedCategorySet() {
        return alreadyAddedCategorySet;
    }

    public void setAlreadyAddedCategorySet(Set<String> alreadyAddedCategorySet) {
        this.alreadyAddedCategorySet = alreadyAddedCategorySet;
    }

    public Set<String> getAlreadyAddedProductSet() {
        return alreadyAddedProductSet;
    }

    public void setAlreadyAddedProductSet(Set<String> alreadyAddedProductSet) {
        this.alreadyAddedProductSet = alreadyAddedProductSet;
    }

    public List<BasicPageLinkElement> readPage(String url) throws Exception {
        List<BasicPageLinkElement> pageLinkElements = new LinkedList<BasicPageLinkElement>();

        Elements pagelinks = getPageHyperLink(htmlParser.parseURL(url));

        if (pagelinks.size() != 0){
            for (Element link : pagelinks){
                String href = getHREFAttribute(link);

                GenericCategoriesType categoriesType = getCategoryType(href);
                if (categoriesType != null){
                    String categoryId = getCategoryId(href, categoriesType);
                    addNewCategory(pageLinkElements, href, categoryId);
                }

                GenericProductsType productsType = getProductType(href);
                if(productsType != null){
                    String productID = getProductId(href, productsType);
                    addNewProduct(pageLinkElements, href, productID);
                }
            }
        }

        return pageLinkElements;
    }

    private String getHREFAttribute(Element link) {
        return link.attr("href");
    }

    private Elements getPageHyperLink(Document document) {
        return document.select("a[href]");
    }

    public GenericCategoriesType getCategoryType(String url) {
        return null;
    }

    public String getCategoryId(String url, GenericCategoriesType categoryType) {
        return null;
    }

    public void addNewCategory(List<BasicPageLinkElement> elements, String url, String categoryId) {
        if (!alreadyAddedCategorySet.contains(categoryId)){
            PageLinkCategoryElement plce = new PageLinkCategoryElement();
            plce.setId(categoryId);
            if (!url.contains(htmlParser.getBaseUri())){
                plce.setAbsoluteHrefValue(htmlParser.getBaseUri() + url);
            }else {
                plce.setAbsoluteHrefValue(url);
            }

            alreadyAddedCategorySet.add(categoryId);
            elements.add(plce);
        }

    }

    public GenericProductsType getProductType(String url) {
        return null;
    }

    public String getProductId(String url, GenericProductsType productType) {
        return null;
    }

    public void addNewProduct(List<BasicPageLinkElement> elements, String url, String productId) {
        if (!alreadyAddedProductSet.contains(productId)){
            PageLinkProductElement plpe = new PageLinkProductElement();
            plpe.setId(productId);
            if (!url.contains(htmlParser.getBaseUri())){
                plpe.setAbsoluteHrefValue(htmlParser.getBaseUri() + url);
            }else {
                plpe.setAbsoluteHrefValue(url);
            }

            alreadyAddedProductSet.add(productId);
            elements.add(plpe);
        }

    }
    @SuppressWarnings({"rawtypes", "unchecked"})

    protected String getAttributeValueFromLinkText(Document doc, String productId, Class attributeClass){
        List<Enum> enumList = EnumUtils.getEnumList(attributeClass);
        for (Enum productTitleEnum : enumList){
            Elements pagelinks = doc.select(productTitleEnum.toString());
            if ((pagelinks.text() != null) && (!"".equals(pagelinks.text().trim()))){
                return pagelinks.text();
            }
        }

        return  null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})

    protected String getAttributeValueFromLinkHtml(Document doc, String productId, Class attributeClass){
        List<Enum> enumList = EnumUtils.getEnumList(attributeClass);
        for (Enum productTitleEnum : enumList){
            String productTagSearch = productTitleEnum.toString();
            if (productTagSearch.contains("PRODUCT_ID")){
                productTagSearch = productTagSearch.replaceAll("PRODUCT_ID", productId);
            }
            Elements pagelinks = doc.select(productTagSearch);
            if ((pagelinks.html() != null) && (!"".equals(pagelinks.text().trim()))){
                return pagelinks.html();
            }
        }

        return  null;
    }

    protected String getAttributeValueFromMetaContentByName(Document doc, String lookinUpAttribute, String getValueFromAttrubute){
        String selectorParameter = "meta[name=" + lookinUpAttribute + "]";
        Elements pagelinks = doc.select(selectorParameter);
        if (!pagelinks.isEmpty()){
            return pagelinks.attr(getValueFromAttrubute);
        }
        return  null;
    }

    public PageLinkProductElement populateProductElementsAttributes(PageLinkProductElement plpe) {
        Document doc = htmlParser.parseURL(plpe.getAbsoluteHrefValue());
        plpe.setPrice(getProductPrice(doc, plpe.getId()));
        plpe.setTitle(getProductTitle(doc, plpe.getId()));
        plpe.setDescription(getProductDescription(doc, plpe.getId()));
        log.info(plpe.toString());
        return plpe;
    }

    protected abstract Double getProductPrice(Document doc, String id);
    protected abstract String getProductTitle(Document doc, String id);
    protected abstract String getProductDescription(Document doc, String id);


    @SuppressWarnings({"rawtypes", "unchecked"})

    public boolean isExcludedProduct(PageLinkProductElement plpe) {
        Document doc = htmlParser.parseURL(plpe.getAbsoluteHrefValue());
        List<Enum> enumList = EnumUtils.getEnumList(getExcludedProductClass());
        for (Enum nonProductEnum : enumList){
            Elements pagelinks = doc.select(nonProductEnum.toString());
            if ((pagelinks.html() != null) && (pagelinks.size() > 0)){
                log.info("WATCH OUT! The product ID: [" + plpe.getId() + "] has been excluded. Reason: ["
                + nonProductEnum.name() + "]");
                return true;
            }
        }
        return false;
    }

    protected abstract Class getExcludedProductClass();

    public PagePageAnalyzerImpl(Set<String> alreadyAddedCategorySet, Set<String> alreadyAddedProductSet, HTMLParser htmlParser)
        throws IOException{
        setAlreadyAddedCategorySet(alreadyAddedCategorySet);
        setAlreadyAddedProductSet(alreadyAddedProductSet);
        setHtmlParser(htmlParser);
    }
}
