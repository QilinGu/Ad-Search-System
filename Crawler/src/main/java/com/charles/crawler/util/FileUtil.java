package com.charles.crawler.util;

import com.charles.crawler.model.entity.Product;
import com.charles.crawler.sync.PageParserThread;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by ChenCheng on 1/3/2017.
 */
public class FileUtil {

    public static Log log = LogFactory.getLog(PageParserThread.class);

    public static void createTSV(List<Product> listToPrint){
        BufferedWriter bw = null;


        File tsv = new File("Product.tsv");
        try {
            log.info("->-> Starting method Utils.createTsv()");
            bw = new BufferedWriter(new FileWriter(tsv));
            bw.write("TITLE" + String.valueOf('\t') + "PRICE" + String.valueOf('\t') +
                    "URL" + String.valueOf('\t') + "DESCRIPTION");
            bw.newLine();

            for (Product element : listToPrint){
                bw.write(element.toString());
                bw.newLine();
            }
            log.debug("TSV file written in the file referenced by path : "
                    + tsv.getAbsolutePath());

        } catch (IOException e) {

        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            log.info("->-> Ending method Utils.createTsv()");
        }

    }

}
