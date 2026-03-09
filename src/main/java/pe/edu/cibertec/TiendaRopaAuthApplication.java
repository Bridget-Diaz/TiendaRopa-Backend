package pe.edu.cibertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "pe.edu.cibertec.feign")
public class TiendaRopaAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaRopaAuthApplication.class, args);
	}

}
