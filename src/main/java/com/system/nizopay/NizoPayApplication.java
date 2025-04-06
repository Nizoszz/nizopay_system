package com.system.nizopay;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = @Server(url = "/", description = "Default Server Url"))
@SpringBootApplication
public class NizoPayApplication{

	public static void main(String[] args) {
		SpringApplication.run(NizoPayApplication.class,args);
	}

}
