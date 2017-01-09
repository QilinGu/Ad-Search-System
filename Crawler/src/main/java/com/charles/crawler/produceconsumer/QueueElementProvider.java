package com.charles.crawler.produceconsumer;

import com.charles.crawler.model.entity.BasicPageLinkElement;
import com.charles.crawler.model.entity.PageLinkCategoryElement;
import com.charles.crawler.model.entity.PageLinkProductElement;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ChenCheng on 1/9/2017.
 */
public class QueueElementProvider {

    private BlockingQueue<PageLinkProductElement> abpProductQueue = null;
    private BlockingQueue<PageLinkCategoryElement> abpCategoryQueue = null;

    public BlockingQueue<PageLinkProductElement> getAbpProductQueue() {
        return abpProductQueue;
    }

    public void setAbpProductQueue(
            BlockingQueue<PageLinkProductElement> abpProductQueue) {
        this.abpProductQueue = abpProductQueue;
    }

    public BlockingQueue<PageLinkCategoryElement> getAbpCategoryQueue() {
        return abpCategoryQueue;
    }

    public void setAbpCategoryQueue(
            BlockingQueue<PageLinkCategoryElement> abpCategoryQueue) {
        this.abpCategoryQueue = abpCategoryQueue;
    }

    /**
     *
     * Method that add a PageLinkCategoryElement into the Category Queue. This
     * PageLinkCategoryElement will be populated merely with the URL value
     * attribute.
     *
     * @param url
     *            URL of the to be added to the queue
     * @return the elements inserted in the Queue
     */
    public int provideCategoryQueue(String url) {
        PageLinkCategoryElement be = new PageLinkCategoryElement();
        be.setAbsoluteHrefValue(url);
        return addElementToQueue(be);
    }

    /**
     *
     * Method that add a PageLinkProductElement into the Product Queue. This
     * PageLinkProductElement will be populated merely with the URL value
     * attribute.
     *
     * @param url
     *            URL of the to be added to the queue
     * @return the elements inserted in the Queue
     */
    public int provideProductQueue(String url) {
        PageLinkProductElement be = new PageLinkProductElement();
        be.setAbsoluteHrefValue(url);
        return addElementToQueue(be);
    }

    /**
     *
     * Method that adds a BasicPageLinkElements into the proper Queue based in
     * the hierarchical class of the element.
     *
     * @param be
     *            BasicPageLinkElement to be added to the queue
     * @return the elements inserted in the proper Queue
     */
    public int provideQueue(BasicPageLinkElement be) {
        return addElementToQueue(be);
    }

    /**
     *
     * Method that adds a a list of BasicPageLinkElements into the proper Queue
     * based in the hierarchical class of the element.
     *
     * @param elements
     *            list of BasicPageLinkElement to be added to the queue
     * @return the elements inserted in the proper Queue
     */
    public int provideQueue(List<BasicPageLinkElement> elements) {
        int elementsAdded = 0;
        for (BasicPageLinkElement be : elements) {
            elementsAdded += addElementToQueue(be);
        }
        return elementsAdded;
    }

    /**
     *
     * Method that adds a BasicPageLinkElements into the proper Queue based in
     * the hierarchical class of the element.
     *
     * @param be
     *            BasicPageLinkElement to be added to the queue
     * @return the elements inserted in the proper Queue
     */
    private int addElementToQueue(BasicPageLinkElement be) {
        int elementsAdded = 0;
        if (be instanceof PageLinkCategoryElement) {
            try {
                getAbpCategoryQueue().put((PageLinkCategoryElement) be);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            elementsAdded++;
        } else if (be instanceof PageLinkProductElement) {
            try {
                getAbpProductQueue().put((PageLinkProductElement) be);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            elementsAdded++;
        }
        return elementsAdded;
    }

    /**
     *
     * Method that gets the size of the Product Queue
     *
     * @return size of the Product Queue
     */
    public int getProductQueueNumberOfElements() {
        return (getAbpProductQueue() == null) ? 0 : (getAbpProductQueue()
                .size());
    }

    /**
     *
     * Method that gets the size of the Category Queue
     *
     * @return size of the Category Queue
     */
    public int getCategoryQueueNumberOfElements() {
        return (getAbpCategoryQueue() == null) ? 0 : (getAbpCategoryQueue()
                .size());
    }}
