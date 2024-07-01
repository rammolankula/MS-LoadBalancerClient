package learn.ram.consumer;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GstConsumer {
	
	//Autowire load balancer client
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	public String getDetailsFromGST(){
		//2.get one serviceInstance by using serviceId
		ServiceInstance si =loadBalancerClient.choose("LBCGstService-Provider");
		
		//3.Read URI from si
		URI uri=si.getUri();
		
		//4.Add path to get URL
		String url=uri+"/gst/data";
		
		//5. use restTemplate 
		RestTemplate rt=new RestTemplate();
		
		//6.make HTTP CALL
		String response= rt.getForObject(url, String.class);
		
		return response;
		
	}
}
