package com.sample.spring.cache.service;

import com.sample.spring.cache.model.Product;

public interface ProductService {
	
	Product getByName(String name);

	public void refreshAllProducts();
	
	Product updateProduct(Product product);

}
