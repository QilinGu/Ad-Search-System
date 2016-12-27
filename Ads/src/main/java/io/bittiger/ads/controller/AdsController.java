package io.bittiger.ads.controller;

import io.bittiger.ads.entity.Ad;
import io.bittiger.ads.webapi.WebApi;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by ChenCheng on 9/25/2016.
 * 
 */
@RestController
public class AdsController {

	@Resource(name = "webapi")
	private WebApi webapi;

	@RequestMapping(value = "/ads/{adId}", method = RequestMethod.GET)
	public ResponseEntity<Ad> getAds(@PathVariable("adId") String adid) {

		Ad ads = this.webapi.getAppService().readAds(adid);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Ad>(ads, headers, HttpStatus.OK);
		
	}
	
	
}
