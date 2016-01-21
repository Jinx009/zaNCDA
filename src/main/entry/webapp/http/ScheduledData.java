package main.entry.webapp.http;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import service.basicFunctions.OrderService;

@Component
@Lazy(value=false)
public class ScheduledData {

	@Autowired
	private OrderService orderService;
	
	@Scheduled(cron = "0 0 18 * * ?")//每天凌晨7点整
    public void orderOne() throws Exception {
    	orderService.checkOne();
    }
	
	@Scheduled(cron = "0 0 18 * * ?")//每天凌晨7点整
    public void orderTwo() throws Exception {
    	orderService.checkTwo();
    }
	 
	@Scheduled(cron = "0 0 18 * * ?")//每天凌晨7点整
    public void orderThree() throws Exception {
    	orderService.checkThree();
    }
	 
	@Scheduled(cron = "0 0 18 * * ?")//每天凌晨7点整
    public void orderFour() throws Exception {
    	orderService.checkFour();
    }
}
