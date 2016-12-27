package io.bittiger.ads.webapi;

import io.bittiger.ads.entity.Ad;

/**
 * Created by ChenCheng on 11/3/2016.
 */
public interface AppService {
    Ad createAds(Ad obj);
    Ad readAds(String adId);
    Ad updateAds(Ad obj);
    Ad deleteAds(Ad obj);

}
