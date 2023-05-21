package orbag.server;

import orbag.server.EnableOrbagServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import orbag.EnableOrbagCore;

@SpringBootApplication
@EnableOrbagCore
@EnableOrbagServer
public class ServerMain {

    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }
}
