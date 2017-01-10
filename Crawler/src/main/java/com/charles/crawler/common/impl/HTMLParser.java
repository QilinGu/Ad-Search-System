package com.charles.crawler.common.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by ChenCheng on 1/10/2017.
 */
public class HTMLParser {

    private final Log log = LogFactory.getLog(HTMLParser.class);

    private String baseUri = null;

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUrl) {
        this.baseUri = baseUri;
    }

    public Document parse(String documentToParse){
        Document doc = Jsoup.parse(documentToParse);
        return doc;
    }

    public Document parseURL(String urlToParse){
        String docToParse = connect(urlToParse);
        Document doc = Jsoup.parse(docToParse);
        return doc;
    }

    private String connect(String urlToParse) {
        String respBody = null;
        RequestConfig config = RequestConfig.custom().setCircularRedirectsAllowed(true).setMaxRedirects(5).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

        HttpGet httpGet = new HttpGet(urlToParse);
        log.debug("Executing Http Get Request: " + httpGet.getURI());
        setBaseUri(httpGet.getURI().toString());

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300){
                    HttpEntity httpEntity = httpResponse.getEntity();
                    return httpEntity != null ? EntityUtils.toString(httpEntity) : null;
                }
                else{
                    throw new ClientProtocolException("Unexpected response status:" + status);
                }
            }
        };

        try {
            respBody = httpClient.execute(httpGet, responseHandler);
        }catch (ClientProtocolException e){
            log.error("ClientProtocolException at method HTMLParser.connect() : " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) {
            log.error("IOException at method HTMLParser.connect() : " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                log.error("IOException at method HTMLParser.connect() : " + e.getMessage());
                e.printStackTrace();
            }
        }


        return respBody;
    }
}
