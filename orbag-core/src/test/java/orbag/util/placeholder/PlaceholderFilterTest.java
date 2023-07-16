package orbag.util.placeholder;

import orbag.EnableOrbagCore;
import orbag.security.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EnableOrbagCore
public class PlaceholderTest {

    @Configuration
    static class ConfigurationClass {

    }

    @ManagedPlaceholder(id_prefix = "test/")
    static class ClassWithRestrictions {

    }
    static class ClassWithoutRestrictions {

    }


    @Test
    public void testGrantsImplicit() {
        Authentication user = new TestingAuthenticationToken("test","test", "authority");
        Grants grants = PermissionsUtils.getAccess(user,ClassWithoutRestrictions.class);
        assertTrue(grants.hasAccess(AccessType.USE));
        assertTrue(grants.hasAccess(AccessType.READ));
        assertFalse(grants.isExplicit());
    }
        @Test
    public void testGrantsExplicit() {

        Authentication user = new TestingAuthenticationToken("test","test", "authority");
        Grants grants =PermissionsUtils.getAccess(user,ClassWithRestrictions.class);
        assertTrue(grants.hasAccess(AccessType.USE));
        assertFalse(grants.hasAccess(AccessType.READ));
        assertTrue(grants.isExplicit());

        Authentication userWithoutAccess = new TestingAuthenticationToken("test","test","authority2");
        Grants grantsUserWithoutAccess =PermissionsUtils.getAccess(userWithoutAccess,ClassWithRestrictions.class);
        assertFalse(grantsUserWithoutAccess.hasAccess(AccessType.USE));

    }
}
