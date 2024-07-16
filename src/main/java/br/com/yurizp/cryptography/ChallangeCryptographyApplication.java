package br.com.yurizp.cryptography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "br.com.yurizp")
@SpringBootApplication(scanBasePackages = "br.com.yurizp")
public class ChallangeCryptographyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeCryptographyApplication.class, args);
	}

}
