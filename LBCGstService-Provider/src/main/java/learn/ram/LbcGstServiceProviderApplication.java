package learn.ram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LbcGstServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(LbcGstServiceProviderApplication.class, args);
	}

}
