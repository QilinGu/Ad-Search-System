package io.bittiger.ads.api;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import io.bittiger.ads.entity.Ad;
import io.bittiger.ads.entity.Campaign;

import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by lieyongzou on 9/8/16.
 * A class to connect with local MongoDB
 * Table Campaign: _id, compaignName, budget
 * Table Ad: _id, campaignId, keywords, clickCount, displayCount, bid, url, content
 * Table inverted index: _id, keyword, adId
 * 
 * pClick = clickCount / displayCount
 */
public class DBConnector {

    private static final String SERVERADDR = "localhost";
    private static final int PORTNUMBER = 27017;
    private static final int INITCLICKCOUNT = 1;
    private static final int INITDISPLAYCOUNT = 2;
    private static final String DBNAME = "ADSEARCH";
    private static final String ADS_COLLECTION = "ADS";
    private static final String CAMPAIGNS_COLLECTION = "CAMPAIGNS";
    private static final String KEYWORDS_COLLECTION = "KEYWORDS";

    // The connection to the ads collection
    private MongoCollection<Document> adCollection;
    private MongoCollection<Document> campaignCollection;
    private MongoCollection<Document> keywordCollection;

    public DBConnector() {

        // Connect to the local mongodb server,
        MongoClient mongoClient = new MongoClient(SERVERADDR, PORTNUMBER);

        // Connect to the database
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DBNAME);

        // Connect to the collection
        adCollection = mongoDatabase.getCollection(ADS_COLLECTION);
        campaignCollection = mongoDatabase.getCollection(CAMPAIGNS_COLLECTION);
        keywordCollection = mongoDatabase.getCollection(KEYWORDS_COLLECTION);

    }

    /**
     * A public method to create a new campaign
     * @param campaignName the name of campaign
     * @param budget the expected budget
     * @return the objectid of newly created campaign
     */
    public Campaign createCampaign(String campaignName, double budget) {

        //Create the document contains all the informations
        Document doc = new Document();
        doc.append("campaignName", campaignName);
        doc.append("budget", budget);

        // insert to the db
        campaignCollection.insertOne(doc);
        return new Campaign(doc.get("_id").toString(), campaignName, budget);
    }

    /**
     * A method to get a list of campaigns owned by the user
     * @return a list of campaigns
     */
    public List<Campaign> getCampaigns() {

        // Get all the campaigns
        FindIterable<Document> findIterable = campaignCollection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        List<Campaign> campaigns = new ArrayList<>();
        while (mongoCursor.hasNext()) {
        	Document doc = mongoCursor.next();
            campaigns.add(new Campaign(doc.get("_id").toString(), doc.getString("campaignName"), doc.getDouble("budget")));
        }

        return campaigns;
    }

    /**
     * A method to get the campaign
     * @param campaignId the id of campaign
     * @return the campaign
     */
    public Campaign getCampaignsById(String campaignId) {

        // Get all the campaigns
        FindIterable<Document> findIterable = campaignCollection.find(new BasicDBObject("_id", new ObjectId(campaignId)));
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        if (mongoCursor.hasNext()) {
        	Document doc = mongoCursor.next();
            return new Campaign(doc.get("_id").toString(), doc.getString("campaignName"), doc.getDouble("budget"));
        } else {
            return new Campaign();
        }
    }

    /**
     * A method to update the budget of campaign
     * @param campaignId the id of campaign
     */
    public void updateBudget(String campaignId, double budget) {

        campaignCollection.updateOne(new BasicDBObject("_id", new ObjectId(campaignId)),
                new BasicDBObject("$set", new BasicDBObject("budget", budget)));
    }


    /**
     * A method to create ad in a campaign
     * @param campaignId the id of campaign
     * @param keywords a list of keywords
     * @param bid the bid of ad
     * @param url the external url of ad
     * @param content the content of ad
     */
    public Ad createAd(String campaignId, String keywords, double bid, String url, String content) {

        Document doc = new Document();
        doc.append("campaignId", campaignId);
        doc.append("keywords", keywords);
        doc.append("bid", bid);
        doc.append("clickCount", INITCLICKCOUNT);
        doc.append("displayCount", INITDISPLAYCOUNT);
        doc.append("url", url);
        doc.append("content", content);

        adCollection.insertOne(doc);

        // Add the keyword to keyword collection
        String adId = doc.get("_id").toString();
        String[] keywordArray = keywords.split(",");

        for (String keyword : keywordArray) {
            insertKeyword(keyword.trim().toLowerCase(), adId);
        }

        return new Ad(doc.get("_id").toString(), campaignId, Arrays.asList(keywordArray), INITCLICKCOUNT, INITDISPLAYCOUNT, bid, url, content);
    }


    /**
     * A method to list all the ads in the campaign
     * @param campaignId the id of campaign
     * @return a list of ads
     */
    public List<Document> getAdsByCampaign(String campaignId) {

        // Get all the ads
        FindIterable<Document> findIterable = adCollection.find(new BasicDBObject("campaignId", campaignId));
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        List<Document> res = new ArrayList<>();
        while (mongoCursor.hasNext()) {
            res.add(mongoCursor.next());
        }

        return res;
    }

    /**
     * A method to get a list of adId according to the keyword
     * @param keyword the keyword
     * @return a list of ad id
     */
    public List<String> getAdsByKeyword(String keyword) {

        FindIterable<Document> findIterable = keywordCollection.find(new BasicDBObject("keyword", keyword));
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        List<String> adList = new ArrayList<>();

        if (mongoCursor.hasNext()) {
            Document doc = mongoCursor.next();
            adList = (List)doc.get("adId");
        }

        return adList;
    }

    /**
     * A method to get the ad by its id
     * @param adId the id of ad
     * @return the ad
     */
    public Ad getAdsById(String adId) {
        FindIterable<Document> findIterable = adCollection.find(new BasicDBObject("_id", new ObjectId(adId)));
        MongoCursor<Document> mongoCursor = findIterable.iterator();

        if (mongoCursor.hasNext()) {
        	Document doc = mongoCursor.next();
            return new Ad(doc.get("_id").toString(), doc.getString("campaignId"), splitKeywords(doc.getString("keywords")), 
            		doc.getInteger("clickCount"), doc.getInteger("displayCount"), doc.getDouble("bid"), doc.getString("url"), 
            		doc.getString("content"));
        }
        return new Ad();
    }


    /**
     * A private method to insert keyword to db
     * @param keyword the keyword
     * @param adId the id of ad contains that keyword
     */
    private void insertKeyword(String keyword, String adId) {

        // If the target keyword is not in the DB, then insert the keyword
        UpdateOptions option = new UpdateOptions();
        option.upsert(true);
        keywordCollection.updateOne(new BasicDBObject("keyword", keyword),
                new BasicDBObject("$push", new BasicDBObject("adId", adId)), option);
    }


    /**
     * A method to split the keywords into a list
     * @param keywords s sentence of keywords
     * @return a list of keywords
     */
    private List<String> splitKeywords(String keywords) {
    	
    	String[] res = keywords.trim().split(",");
    	return Arrays.asList(res);
    	
    }


    public static void main(String[] args) {
        DBConnector dbConnector = new DBConnector();

//        dbConnector.createCampaign("Woman Shoes", 11000);
//        dbConnector.updateBudget("57d21ec904d3f905a6835b92", 11000);
//        System.out.println(dbConnector.getCampaigns());
//        System.out.println(dbConnector.getcampaignsById("57d21ec904d3f905a6835b92"));


//        dbConnector.createAd("57ddc5a7ef69fd09f56c36d8", "Woman,shoes", 30, "", "");
//        System.out.println(dbConnector.getAdsInCampaign("57ddc5a7ef69fd09f56c36d8"));
        for (String adId : dbConnector.getAdsByKeyword("shoes")) {
            System.out.println(dbConnector.getAdsById(adId));
        }
    }
}
