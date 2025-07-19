package com.snappyshop.recommender.useractivity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.snappyshop.recommender.useractivity.DTO.RecommendationResponse;

@RestController
@RequestMapping("/view-product")
public class UserActivityController {
	
	private final RestTemplate restTemplate;
	
	@Value("${recommendation.service.url}")
	private String recommendationServiceUrl;
	
	public UserActivityController(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@GetMapping("/{userId}/{productId}")
	public List<String> viewProduct(@PathVariable String userId, @PathVariable String productId ){
		String url = recommendationServiceUrl+"/recommend/"+userId+"/"+productId;
		long start = System.currentTimeMillis();
		RecommendationResponse recommendations = restTemplate.getForObject(url, RecommendationResponse.class);
		long end = System.currentTimeMillis();
		System.out.println("Recommendation response time: "+(end-start)+" ms");
		//System.out.println(" outputttt... "+recommendations.getProductTitle());
		return recommendations.getRecommendations();
	}

}
