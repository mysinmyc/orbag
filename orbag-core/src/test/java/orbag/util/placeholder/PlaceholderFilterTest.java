package orbag.util.placeholder;

import orbag.EnableOrbagCore;
import orbag.security.*;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableOrbagCore
public class PlaceholderFilterTest {

    @Autowired
    VisibilityManager visibilityManager;

    @Configuration
    @EnableOrbagCore
    static class ConfigurationClass {

    }

    @ManagedPlaceholder(id_prefix = "test/")
    static class ClassWithRestrictions {

    }
    static class ClassWithoutRestrictions {

    }


    @Test
    public void testFilter() {
        PlaceholderConfigurationItem placeholderCi = new PlaceholderConfigurationItem();
        placeholderCi.setIdentifier("test/ciao");
        assertEquals(1,visibilityManager.filterClasses(List.of(ClassWithRestrictions.class), FilterContext.forTargetObject(placeholderCi)).size());

        PlaceholderConfigurationItem placeholderKo = new PlaceholderConfigurationItem();
        placeholderCi.setIdentifier("ko/ciao");
        assertEquals(0,visibilityManager.filterClasses(List.of(ClassWithRestrictions.class), FilterContext.forTargetObject(placeholderKo)).size());
    }

}
