package com.charles.crawler.sync;

import com.charles.crawler.common.interfaces.PagePageAnalyzer;
import com.charles.crawler.model.entity.PageLinkProductElement;
import com.charles.crawler.model.service.interfaces.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public class PageParserThread implements Runnable{
    private final Log log = LogFactory.getLog(PageParserThread.class);
    private BlockingQueue<PageLinkProductElement> abpProductQueue = null;
    private ProductService productService = null;
    private PagePageAnalyzer pagePageAnalyzer = null;

    public Log getLog() {
        return log;
    }

    public BlockingQueue<PageLinkProductElement> getAbpProductQueue() {
        return abpProductQueue;
    }

    public void setAbpProductQueue(BlockingQueue<PageLinkProductElement> abpProductQueue) {
        this.abpProductQueue = abpProductQueue;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public PagePageAnalyzer getPagePageAnalyzer() {
        return pagePageAnalyzer;
    }

    public void setPagePageAnalyzer(PagePageAnalyzer pagePageAnalyzer) {
        this.pagePageAnalyzer = pagePageAnalyzer;
    }

    public void run() {

    }
}
