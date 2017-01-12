package com.charles.crawler.common.interfaces;

/**
 * Created by ChenCheng on 1/12/2017.
 */
public interface Parser {
    Object parse(String documentToParse);
    Object parseURL(String urlToParse);
}
