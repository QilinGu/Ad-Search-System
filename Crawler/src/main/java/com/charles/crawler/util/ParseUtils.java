package com.charles.crawler.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ChenCheng on 1/11/2017.
 */
public class ParseUtils {

    private static Log log = LogFactory.getLog(ParseUtils.class);

    public static Double parsePrice(String priceToBeParsed){
        String price = null;
        Double dPrice = new Double(0.00);
        if ((priceToBeParsed != null) && (!"".equals(priceToBeParsed.trim()))){
            Pattern p = Pattern.compile("[\\d\\.]+");
            Matcher m = p.matcher(priceToBeParsed);
            if (m.find()){
                price = m.group();
            }
            dPrice = new Double(price);
        }

        return dPrice;
    }
}
