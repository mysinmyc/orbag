package orbag.server;

import orbag.orchestration.EnableOrchestration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import orbag.EnableOrbagCore;
import orbag.impl.EnableImpl;

@SpringBootApplication
@EnableOrbagCore
@EnableImpl
@EnableOrchestration
public class ServerMain {

	public static void main(String[] args) {
		SpringApplication.run(ServerMain.class, args);
	}
}
