package cn.net.arven.photography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableEurekaClient
@Controller
@ServletComponentScan
public class PhotographyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PhotographyApplication.class, args);
    }
    @RequestMapping("/")
    public Object home(){
        return "index";
    }

    @RequestMapping("/index.html")
    public Object index(){
        return "index";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PhotographyApplication.class);
    }
}
