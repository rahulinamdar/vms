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
	@Scheduled(cron="0 26 0 * * ?")
    public void createPricingEntry() throws ParseException
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
        productService.createPricingEntry();
    }
	
	/**
	 * @author rahul
	 * 
	 * @throws ParseException
	 */
	@Scheduled(cron="0 26 0 * * ?")
    public void createStock() throws ParseException
    {
        System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
        productService.createStock();
    }
}
