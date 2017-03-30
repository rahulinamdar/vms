/**
 * 
 */
package com.ims.scheduler;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.ims.service.ProductService;

/**
 * @author rahul
 *
 */
public class PriceScheduler {
	
	@Autowired
	ProductService productService;
	
	/**
	 * @author rahul
	 * 
	 * @throws ParseException
	 */
	@Scheduled(cron="0 0 1 * * ?")
    public void demoServiceMethod() throws ParseException
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
        productService.createPricingEntry();
    }
}
