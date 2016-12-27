package io.bittiger.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.bittiger.ads.entity.Ad;

/**
 * A class to filter out ads with low similarity
 * @author lieyongz
 * 
 * Regulation:
 * 1, relevance Score < 0.3
 * 2, bid < 1.5
 * 3, select top k
 * 4, Dedup, reduce the ads from the same campaign
 *
 */
public class AdFilter {

	private int k;

	public AdFilter(int k) {
		this.k = k;
	}

	/**
	 * Filter the ads from the list.
	 * @param ads
	 * @return
	 */
	public List<Ad> execute(List<Ad> ads) {
		
		// The list contains all the ads that are filtered out
		List<Ad> removeAds = new ArrayList<>();
		
		// Filter the ads
		for (Ad ad : ads) {
			if (ad.getRelevanceScore() < 0.3 || ad.getBid() < 1.5 ) {
				removeAds.add(ad);
			}
		}
		
		ads.removeAll(removeAds);
		
		// Sort the ads
		Collections.sort(ads);
		
		// Filter out the ads from same campaign
		Set<String> campaignSet = new HashSet<>();
		removeAds.clear();
		
		for (Ad ad : ads) {
			if (campaignSet.contains(ad.getCampaignId())) {
				removeAds.add(ad);
			} else {
				campaignSet.add(ad.getCampaignId());
			}
		}
		
		ads.removeAll(removeAds);
		

		// Get the first k ads
		if (ads.size() > k) {
			ads.subList(k, ads.size()).clear();
		}
		
		return ads;
	}
	
}
