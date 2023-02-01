package com.mywallet.myUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MyUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyUserApplication.class, args);
	}

}
