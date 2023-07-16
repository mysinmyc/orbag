package orbag.security;

import orbag.EnableOrbagCore;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@EnableOrbagCore
public class ConfigurationItemDaoTest {

    @Configuration
    static class ConfigurationClass {

    }
}
