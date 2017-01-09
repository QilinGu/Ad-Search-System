package com.charles.crawler.sync;

import com.charles.crawler.common.interfaces.PagePageAnalyzer;
import com.charles.crawler.model.entity.BasicPageLinkElement;
import com.charles.crawler.model.entity.PageLinkCategoryElement;
import com.charles.crawler.model.entity.PageLinkProductElement;
import com.charles.crawler.produceconsumer.QueueElementProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ChenCheng on 1/6/2017.
 */
public class PageAnalizerThread implements Runnable{

    private final Log log = LogFactory.getLog(PageAnalizerThread.class);
    private BlockingQueue<PageLinkProductElement> abpProductQueue = null;
    private BlockingQueue<PageLinkCategoryElement> abpCategoryQueue = null;
    private Integer maxProduct = null;
    private PagePageAnalyzer pageAnalizer = null;
    private String initialURL = null;
    private QueueElementProvider queueElementProvider;

    public BlockingQueue<PageLinkProductElement> getAbpProductQueue() {
        return abpProductQueue;
    }

    public void setAbpProductQueue(BlockingQueue<PageLinkProductElement> abpProductQueue) {
        this.abpProductQueue = abpProductQueue;
    }

    public BlockingQueue<PageLinkCategoryElement> getAbpCategoryQueue() {
        return abpCategoryQueue;
    }

    public void setAbpCategoryQueue(BlockingQueue<PageLinkCategoryElement> abpCategoryQueue) {
        this.abpCategoryQueue = abpCategoryQueue;
    }

    public Integer getMaxProduct() {
        return maxProduct;
    }

    public void setMaxProduct(Integer maxProduct) {
        this.maxProduct = maxProduct;
    }

    public PagePageAnalyzer getPageAnalizer() {
        return pageAnalizer;
    }

    public void setPageAnalizer(PagePageAnalyzer pageAnalizer) {
        this.pageAnalizer = pageAnalizer;
    }

    public String getInitialURL() {
        return initialURL;
    }

    public void setInitialURL(String initialURL) {
        this.initialURL = initialURL;
    }

    public PageAnalizerThread(
            BlockingQueue<PageLinkCategoryElement> abpCategoryQueue,
            BlockingQueue<PageLinkProductElement> abpProductQueue,
            PagePageAnalyzer pageAnalizer,
            QueueElementProvider queueElementProvider, Integer maxProduct,
            String initialURL) throws IOException, InterruptedException {
        setAbpCategoryQueue(abpCategoryQueue);
        setAbpProductQueue(abpProductQueue);
        setPageAnalizer(pageAnalizer);
        setQueueElementProvider(queueElementProvider);
        setMaxProduct(maxProduct);
        setInitialURL(initialURL);
    }

    

    public void run() {
        log.info("->-> Starting method PageRetrieverThread.run()");
        try {
			/*
			 * The 'maxProduct' condition is parametrized by a constant in the
			 * ApplicationContext and can be changed
			 */
            queueElementProvider.provideCategoryQueue(getInitialURL());
            while ((abpCategoryQueue.size() > 0)
                    && (abpProductQueue.size() < maxProduct)) {
                PageLinkCategoryElement ce = abpCategoryQueue.take();
                List<BasicPageLinkElement> elements = pageAnalizer.readPage(ce
                        .getAbsoluteHrefValue());
                queueElementProvider.provideQueue(elements);
                // log.info("Category Queue Size : "+abpCategoryQueue.size());
                // log.info("Product Queue Size : "+abpProductQueue.size());
            }
        } catch (IOException e) {
            log.error("IOException at method PageRetrieverThread.run() : "
                    + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            log.error("InterruptedException at method PageRetrieverThread.run() : "
                    + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            // TODO Close all the objects
        }
        log.info("<-<- Ending method PageRetrieverThread.run()");
    }

    public void setQueueElementProvider(QueueElementProvider queueElementProvider) {
        this.queueElementProvider = queueElementProvider;
    }
}
