package io.bittiger.ads.entity;

import java.util.List;

/**
 * Created by ChenCheng on 9/25/2016.
 */
public class Ad implements Comparable<Ad> {

    private String adId;
    private String campaignId;
    private List<String> keywords;
    private int clickCount;
    private int displayCount;
    private double bid;
    private String url;
    private String content;
    
    private double rankScore;
    private double relevanceScore;
    private double qualityScore;
	private double price;


	public Ad() {
		super();
	}


	public Ad(String adId, String campaignId, List<String> keywords, int clickCount, int displayCount, double bid, String url,
			String content) {
		super();
		this.adId = adId;
		this.campaignId = campaignId;
		this.keywords = keywords;
		this.clickCount = clickCount;
		this.displayCount = displayCount;
		this.bid = bid;
		this.url = url;
		this.content = content;
		this.price = 0.0;
	}
    
	
	public String getAdId() {
		return adId;
	}

	
	public String getCampaignId() {
		return campaignId;
	}
	
	
	public List<String> getKeywords() {
		return keywords;
	}


	public void addKeywords(String keyword) {
		this.keywords.add(keyword);
	}


	public double getpClick() {
		return (double) this.clickCount / this.displayCount;
	}
	
	public void addDisplayCount() {
		this.displayCount += 1;
	}
	
	public void addClickCount() {
		this.clickCount += 1;
	}

	public double getBid() {
		return bid;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
	public void setRankScore(double rankScore) {
		this.rankScore = rankScore;
	}
	
	public double getRankScore() {
		return this.rankScore;
	}
	
	public double getRelevanceScore() {
		return relevanceScore;
	}


	public void setRelevanceScore(double relevanceScore) {
		this.relevanceScore = relevanceScore;
	}


	public double getQualityScore() {
		return qualityScore;
	}


	public void setQualityScore(double qualityScore) {
		this.qualityScore = qualityScore;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Rank score: " + this.rankScore + " Ad : " + this.adId + ", " + this.campaignId + ", " + this.keywords + ", " + this.clickCount + ", " + this.displayCount
				+ ", " + this.bid + ", " +  this.url + ", " +  this.content + ", " + this.price;
	}

	@Override
	public int compareTo(Ad o) {
		return ((Double)o.rankScore).compareTo(this.rankScore);
	}




}
