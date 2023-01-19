package com.mywallet.myAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MyAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAccountApplication.class, args);
	}

}
