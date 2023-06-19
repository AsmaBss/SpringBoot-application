package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@EnableSwagger2
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//new File(ImageController.upload().mkdir();
		SpringApplication.run(Application.class, args);
	}

}
