package com.clienteexamen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClienteExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteExamenApplication.class, args);
	}

}
