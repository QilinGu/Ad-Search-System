package com.charles.crawler.model.service.impl;

import com.charles.crawler.common.interfaces.PagePageAnalyzer;
import com.charles.crawler.model.service.interfaces.PageAnalyzerService;
import com.charles.crawler.model.service.interfaces.ProductService;
import com.charles.crawler.sync.PageAnalizerThread;
import com.charles.crawler.sync.PageParserThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;

/**
 * Created by ChenCheng on 1/6/2017.
 */
public class PageAnalyzerServiceImpl implements PageAnalyzerService {
    private final Log log = LogFactory.getLog(PageAnalyzerServiceImpl.class);
    private BlockingQueue<String> abpCategoryQueue = null;
    private BlockingQueue<String> abpProductQueue = null;
    private PageAnalizerThread pageAnalizerThread = null;
    private PageParserThread pageParserThread = null;
    private PagePageAnalyzer pageAnalizer = null;
    private ProductService ProductService = null;


    private ThreadPoolTaskExecutor threadPoolTaskExecutor = null;
    // Number of Retrievers/Producers
    private Integer numberOfPageAnalizers = null;
    // Number of Parsers/Consumers
    private Integer numberOfParsers = null;

    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        return threadPoolTaskExecutor;
    }

    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public Integer getNumberOfPageAnalizers() {
        return numberOfPageAnalizers;
    }

    public void setNumberOfPageAnalizers(Integer numberOfPageAnalizers) {
        this.numberOfPageAnalizers = numberOfPageAnalizers;
    }

    public Integer getNumberOfParsers() {
        return numberOfParsers;
    }

    public void setNumberOfParsers(Integer numberOfParsers) {
        this.numberOfParsers = numberOfParsers;
    }

    public BlockingQueue<String> getAbpCategoryQueue() {
        return abpCategoryQueue;
    }

    public void setAbpCategoryQueue(BlockingQueue<String> abpCategoryQueue) {
        this.abpCategoryQueue = abpCategoryQueue;
    }

    public BlockingQueue<String> getAbpProductQueue() {
        return abpProductQueue;
    }

    public void setAbpProductQueue(BlockingQueue<String> abpProductQueue) {
        this.abpProductQueue = abpProductQueue;
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

    public PagePageAnalyzer getPageAnalizer() {
        return pageAnalizer;
    }

    public void setPageAnalizer(PagePageAnalyzer pageAnalizer) {
        this.pageAnalizer = pageAnalizer;
    }

    public com.charles.crawler.model.service.interfaces.ProductService getProductService() {
        return ProductService;
    }

    public void setProductService(com.charles.crawler.model.service.interfaces.ProductService productService) {
        ProductService = productService;
    }

    public void analizeSiteAndStored() {
        log.info("->-> Starting method PageAnalyzerService.analizeSiteAndStored()");
        for (int i = 0; i < numberOfPageAnalizers; i++) {
            threadPoolTaskExecutor.execute(pageAnalizerThread);
        }

        log.info("Created " + numberOfPageAnalizers + " Retriever threads");

        for (int i = 0; i < numberOfParsers; i++) {
            pageParserThread.setIdThread(i);
            threadPoolTaskExecutor.execute(pageParserThread);
        }

        log.info("Created " + numberOfParsers + " Parser threads");

        threadPoolTaskExecutor.shutdown();

        while (threadPoolTaskExecutor.getActiveCount() != 0) {}


        getProductService().printProductToCsvFileByDefault();
        log.info("<-<- Ending PageAnalizerService.analizeSiteAndStored()");

    }
}
