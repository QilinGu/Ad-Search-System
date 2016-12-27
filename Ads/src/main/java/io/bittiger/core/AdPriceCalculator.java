package io.bittiger.core;

import io.bittiger.ads.entity.Ad;

import java.util.List;

/**
 * A class to calculate all the price of ads
 * @author lieyongz
 * 
 * Algorithms:
 * 1, nextAd.getQualityScore / currentAd.getQualityScore() * bid + 0.01 (Second Price Auction)
 * 2， Base price: $2
 */
public class AdPriceCalculator {

    private static final double BASE_PRICE = 2.0;

    /**
     * Calculate the price of each ads
     * @param ads the list of ads
     * @return the list of ads with its corresponding price
     */
    public List<Ad> execute(List<Ad> ads) {

        // Set the base price to the last ad
        ads.get(ads.size() - 1).setPrice(BASE_PRICE);

        for (int i = ads.size() - 2; i >= 0; i--) {
            double price = ads.get(i).getBid() * ads.get(i).getQualityScore() / ads.get(i + 1).getQualityScore() + 0.01;
            ads.get(i).setPrice(price);
        }
        return ads;
    }
	
}
