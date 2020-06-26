package com.ecommerce.microcommerce;

import com.ecommerce.microcommerce.dao.IContactDao;
import com.ecommerce.microcommerce.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootApplication
@EnableSwagger2
public class MicrocommerceApplication implements CommandLineRunner
{
	@Autowired
	private IContactDao contactDao;

	public static void main(String[] args) {
		SpringApplication.run(MicrocommerceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ecommerce.microcommerce"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Microcommerce Spring Boot REST API Documentation")
				.description("REST APIs For Managing Microcommerce")
				//.contact(new Contact("Georges Kemayo", "https://gkemayo.developpez.com/", "noreply.library.test@gmail.com"))
				.version("1.0")
				.build();

	}

	@Override
	public void run(String... args) throws Exception {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		contactDao.save(new Contact( "Eloumian", "Maro",df.parse("21/02/1967"),
				"maroaslan@hotmail.com", 0626440451, "./Image/IMG_2.jpg"));
		contactDao.save(new Contact( "Derruette", "Eric", df.parse("25/11/1960"),
				"maroaslan@hotmail.com", 0626440450, "./Image/IMG_2.jpg"));

		contactDao.findAll().forEach(a-> {System.out.println(a.getNom());});
	}
}
