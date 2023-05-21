package orbag.samples.server;

import orbag.orchestration.EnableOrchestration;
import orbag.server.EnableOrbagServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import orbag.EnableOrbagCore;
import orbag.samples.EnableSamples;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
@EnableOrbagCore
@EnableSamples
@EnableOrbagServer
@EnableOrchestration
public class SamplesServerMain {



    public static void main(String[] args) {
        SpringApplication.run(SamplesServerMain.class, args);
    }
}
