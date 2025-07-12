package com.snappyshop.recommender.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.snappyshop.recommender.DTO.Product;
import com.snappyshop.recommender.DTO.RecommendationResponse;

@RestController
@RequestMapping("/recommend")
public class RecommendationController {
	private final RestTemplate restTemplate;
	
	public RecommendationController(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
		
	}
	
	@GetMapping("/{userId}/{productId}")
	public RecommendationResponse getRecommendations(@PathVariable String userId, @PathVariable String productId ){
		String url ="https://api.escuelajs.co/api/v1/products/"+productId;
		Product product;
		try {
			product = restTemplate.getForObject(url, Product.class);
		}catch(HttpClientErrorException e) {
			return new RecommendationResponse("Product not found",List.of());
		}
		
	
		if(product == null || product.getCategory()== null) {
			return new RecommendationResponse("no recommended product available ",List.of());
		}
		
		String categoryUrl = "https://api.escuelajs.co/api/v1/categories/"+product.getCategory().getId()+"/products";
		
		
		ResponseEntity<Product[]> response = restTemplate.getForEntity(categoryUrl, Product[].class);
		
		List<String> recommendation = null ;
		if(response.getStatusCode().is2xxSuccessful() && response.getBody()!=null) {
			recommendation = Arrays.stream(response.getBody())
					.filter(p->p.getId() != product.getId())
					.limit(5)
					.map(Product::getTitle)
					.collect(Collectors.toList());
		}
		return new RecommendationResponse(product.getTitle(),recommendation);
		
	}
}
