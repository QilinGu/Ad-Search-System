package io.bittiger.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.bittiger.ads.api.DBConnector;
import io.bittiger.ads.entity.Ad;

/**
 * A class to rank the ads
 * @author lieyongz
 *
 * Algorithms:
 * Rank Score = Quality Score * bid
 * Quality Score = 0.75 * pClick + 0.25 * Relevance Score
 * Relevance Score = hitCount / KeywordSize 
 *
 */
public class AdRanker {

	private List<Ad> ads = new ArrayList<>();


	/**
	 * Execute rank task to rank the ads related to the keywords
	 * @param keywords the list of keywords
	 * @return a list of ads
	 */
	public List<Ad> execute(List<String> keywords) {
		
		List<Ad> ads = new ArrayList<>();
		
		// The connector to mongodb
		DBConnector connector = new DBConnector();
		
		// Build HitCountMap
		Map<String, Integer> hitCountMap = new HashMap<>();
		for (String keyword : keywords) {
			
			// Get the list of id with the keyword
			List<String> ids = connector.getAdsByKeyword(keyword);
			for (String id : ids) {
				hitCountMap.putIfAbsent(id, 1);
				hitCountMap.put(id, hitCountMap.get(id) + 1);
			}
		}
		
		// Calculate the rank score
		for (Map.Entry<String, Integer> entry : hitCountMap.entrySet()) {
			
			Ad ad = connector.getAdsById(entry.getKey());
			double relevanceScore = (double) entry.getValue() / ad.getKeywords().size();
			double qualityScore = 0.75 * ad.getpClick() + 0.25 * relevanceScore;
			double rankScore = qualityScore * ad.getBid();
			
			ad.setRelevanceScore(relevanceScore);
			ad.setQualityScore(qualityScore);
			ad.setRankScore(rankScore);
			
			ads.add(ad);
		}
		
		
		return ads;
	}
	
}
