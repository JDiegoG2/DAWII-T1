package com.cibertec.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Your API Title", version = "1.0", description = "API Description"))
public class DawiiT1GaticaJohanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DawiiT1GaticaJohanApplication.class, args);
	}

}
