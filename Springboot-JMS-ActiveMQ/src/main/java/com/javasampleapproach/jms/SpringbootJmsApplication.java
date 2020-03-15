package com.javasampleapproach.jms;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.javasampleapproach.*")
public class SpringbootJmsApplication  implements CommandLineRunner {
	

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SpringbootJmsApplication.class);		
		app.setBannerMode(Mode.OFF);		
		
		app.run(args);

	}
	
	//access command line arguments
    @Override
    public void run(String... args) throws Exception {

        //do something

    }
}
