package com.charles.crawler;

import com.charles.crawler.model.entity.Product;
import com.charles.crawler.model.service.interfaces.PageAnalyzerService;
import com.charles.crawler.model.service.interfaces.ProductService;
import com.charles.crawler.sync.PageAnalizerThread;
import com.charles.crawler.sync.PageParserThread;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by ChenCheng on 1/8/2017.
 */
public class AmazonCrawler {
    private static final String CONFIG_PATH = "com/charles/crawler/applicationContext.xml";

    private Integer numOfRetrieves = null;

    private Integer numOfParsers = null;
    private PageAnalizerThread pageAnalizerThread = null;

    private PageParserThread pageParserThread = null;
    private List<Product> productList = null;

    private ProductService productService = null;

    private static ApplicationContext context;


    public Integer getNumOfRetrieves() {
        return numOfRetrieves;
    }

    public void setNumOfRetrieves(Integer numOfRetrieves) {
        this.numOfRetrieves = numOfRetrieves;
    }

    public Integer getNumOfParsers() {
        return numOfParsers;
    }

    public void setNumOfParsers(Integer numOfParsers) {
        this.numOfParsers = numOfParsers;
    }

    public PageAnalizerThread getPageAnalizerThread() {
        return pageAnalizerThread;
    }

    public void setPageAnalizerThread(PageAnalizerThread pageAnalizerThread) {
        this.pageAnalizerThread = pageAnalizerThread;
    }

    public PageParserThread getPageParserThread() {
        return pageParserThread;
    }

    public void setPageParserThread(PageParserThread pageParserThread) {
        this.pageParserThread = pageParserThread;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        AmazonCrawler.context = context;
    }

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext(CONFIG_PATH);
        PageAnalyzerService pageAnalyzerService = (PageAnalyzerService) context.getBean("PageAnalyzerService");
        pageAnalyzerService.analizeSiteAndStored();
    }
}
