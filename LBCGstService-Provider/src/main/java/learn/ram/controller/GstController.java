package learn.ram.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gst")
public class GstController {

	@Value("${server.port}")
	private String port;
	
	@GetMapping("/data")
	public String getDetails() {
		return "======>TO GST="+port;
	}
}
