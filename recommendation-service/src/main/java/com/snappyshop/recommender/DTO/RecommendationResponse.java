package com.snappyshop.recommender.DTO;

import java.util.List;

public class RecommendationResponse {
	private String productTitle;
	private List<String> recommendations;
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public List<String> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<String> recommendations) {
		this.recommendations = recommendations;
	}
	public RecommendationResponse(String productTitle, List<String> recommendations) {
		super();
		this.productTitle = productTitle;
		this.recommendations = recommendations;
	}
}
