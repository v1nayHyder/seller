package com.tech.vinay.seller;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SellerApplication {

	public static void main(String[] args) {

		SpringApplication.run(SellerApplication.class, args);
		System.out.println("This my enam-seller application");
		String javaVersion = System.getProperty("java.version");

		// Print the Java version
		System.out.println("Java Version: " + javaVersion);

	}
	@Bean
	public PlatformTransactionManager transactionManager (EntityManagerFactory entityManagerFactory){
		return new JpaTransactionManager(entityManagerFactory);
	}
}
