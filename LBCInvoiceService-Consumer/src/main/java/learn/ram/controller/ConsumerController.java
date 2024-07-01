package learn.ram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.ram.consumer.GstConsumer;

@RestController
@RequestMapping("/invoice")
public class ConsumerController {
	
	@Autowired
	GstConsumer consumer;
	
	@GetMapping("/info")
	public String getShowInfo() {
		return "FROM CONSUMER"+consumer.getDetailsFromGST();
	}

}
