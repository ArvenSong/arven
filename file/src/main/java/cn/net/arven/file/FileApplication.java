package cn.net.arven.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
//@EnableEurekaClient
@Controller
@ServletComponentScan
public class FileApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }

    @RequestMapping("/")
    public Object home(){
        return "index";
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FileApplication.class);
    }
}
