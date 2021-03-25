package pl.jakub.webchatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Web Chat Server Spring Boot application
 * used to forward messages to all chat room
 * subscribers.
 *
 * @author Jakub Zelmanowicz
 */
@SpringBootApplication
public class WebchatserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebchatserverApplication.class, args);
	}

}
