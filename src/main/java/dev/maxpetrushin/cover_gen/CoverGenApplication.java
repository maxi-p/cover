package dev.maxpetrushin.cover_gen;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import dev.maxpetrushin.cover_gen.object.Greeting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CoverGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoverGenApplication.class, args);
	}

	private static final String TEMPLATE = "Hello, %s!";

	@GetMapping("/")
	public HttpEntity<Greeting> helloEndpoint(@RequestParam(value = "name", defaultValue = "Cover Generator") String name){
		Greeting greeting = new Greeting(String.format(TEMPLATE, name));

		greeting.add(linkTo(methodOn(CoverGenApplication.class).helloEndpoint(name)).withSelfRel());

		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}

}
