package com.departamentoexamen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DepartamentoExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartamentoExamenApplication.class, args);
	}

}
