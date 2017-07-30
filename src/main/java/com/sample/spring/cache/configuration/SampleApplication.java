package com.sample.spring.cache.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.sample.spring.cache.model.Product;
import com.sample.spring.cache.service.ProductService;



public class SampleApplication {

	private static final Logger logger = LoggerFactory.getLogger(SampleApplication.class);
    
    public static void main(String[] args){
    	 AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    	 
         ProductService service = (ProductService) context.getBean("productService");
          
         logger.info("IPhone ->" + service.getByName("IPhone"));
         logger.info("IPhone ->" + service.getByName("IPhone"));
         logger.info("IPhone ->" + service.getByName("IPhone"));
  
          
         logger.info("HTC ->" + service.getByName("HTC"));
         logger.info("HTC ->" + service.getByName("HTC"));
         logger.info("HTC ->" + service.getByName("HTC"));
  
         Product product = new Product("IPhone",550);
         service.updateProduct(product);
          
         logger.info("IPhone ->" + service.getByName("IPhone"));
         logger.info("IPhone ->" + service.getByName("IPhone"));
         logger.info("IPhone ->" + service.getByName("IPhone"));
          
          
         logger.info("Refreshing all products");
  
         service.refreshAllProducts();
         logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
         logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
         logger.info("IPhone [after refresh]->" + service.getByName("IPhone"));
  
         ((AbstractApplicationContext) context).close();
     }
}
