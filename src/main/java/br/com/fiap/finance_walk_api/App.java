package br.com.fiap.finance_walk_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "API Finance Walk",
		version = "v1",
		description = "API do SaaS de controle de gastos pessoais",
		contact = @Contact(
			name = "Pedro Novais",
			email = "pedro.novais@b2c.srv.br",
			url = "https://github.com/pedroonovais"
		)
	)
)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
