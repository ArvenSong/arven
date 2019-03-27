package cn.net.arven.tally;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TallyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TallyApplication.class, args);
	}


}
