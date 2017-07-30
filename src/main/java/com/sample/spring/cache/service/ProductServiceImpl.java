package com.sample.spring.cache.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sample.spring.cache.model.Product;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	private static List<Product> products;

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	static {
		products = getDummyProducts();
	}

	@Cacheable(value="products", key="#name", condition="#name!='HTC'" , unless="#result==null")
	public Product getByName(String name) {
		// slowLookupOperation();
		logger.info("<!----------Entering getByName()--------------------->");
		for (Product p : products) {
			if (p.getName().equalsIgnoreCase(name))
				return p;
		}
		return null;
	}

	@CacheEvict(value = "products", allEntries = true)
	public void refreshAllProducts() {
		// This method will remove all 'products' from cache, say as a result of
		// flush API call.
	}

	public void slowLookupOperation() {
		try {
			long time = 5000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
	@CachePut(value = "products", key = "#product.name" , unless="#result==null")
	public Product updateProduct(Product product) {
		 logger.info("<!----------Entering updateProduct ------------------->");
	        for(Product p : products){
	            if(p.getName().equalsIgnoreCase(product.getName()))
	                p.setPrice(product.getPrice());
	                return p;
	        }
	        return null;
	}

	private static List<Product> getDummyProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("IPhone", 500));
		products.add(new Product("Samsung", 600));
		products.add(new Product("HTC", 800));
		return products;
	}

}
