package com.charles.crawler.sync;

import com.charles.crawler.common.interfaces.PagePageAnalyzer;
import com.charles.crawler.model.entity.PageLinkProductElement;
import com.charles.crawler.model.service.impl.ProductServiceImpl;
import com.charles.crawler.model.service.interfaces.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public class PageParserThread implements Runnable{
    private final Log log = LogFactory.getLog(PageParserThread.class);
    private BlockingQueue<PageLinkProductElement> abpProductQueue = null;
    private ProductService productService = null;
    private PagePageAnalyzer pagePageAnalyzer = null;
    private Integer idThread = null;
    private Integer maxProduct = null;

    public Integer getMaxProduct() {
        return maxProduct;
    }

    public void setMaxProduct(Integer maxProduct) {
        this.maxProduct = maxProduct;
    }

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

    public Integer getIdThread() {
        return idThread;
    }

    public void setIdThread(Integer idThread) {
        this.idThread = idThread;
    }

    public PageParserThread(ProductServiceImpl productService, BlockingQueue<PageLinkProductElement> abpProductQueue,
                            PagePageAnalyzer pagePageAnalyzer, Integer threadId, Integer maxProduct)throws IOException{
        setProductService(productService);
        setAbpProductQueue(abpProductQueue);
        setPagePageAnalyzer(pagePageAnalyzer);
        setMaxProduct(maxProduct);
        setIdThread(threadId);
    }
    public void run() {
        log.info("->-> Starting method PageParserThread.run()");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (!abpProductQueue.isEmpty()){
            try {
                PageLinkProductElement nextplpe = abpProductQueue.take();
                if (!pagePageAnalyzer.isExcludedProduct(nextplpe)){
                    PageLinkProductElement plpe = pagePageAnalyzer.populateProductElementsAttributes(nextplpe);
                    getProductService().addProductByElement(plpe);
                }
                log.info("abpProductQueue.size : " + abpProductQueue.size());
            } catch (InterruptedException e) {
                log.error("Exception at method PageParserThread.run() : "+ e.getMessage());
                e.printStackTrace();
            }
        }

        log.info("Thread #" +getIdThread()  + " FINISHED!!");
    }
}
