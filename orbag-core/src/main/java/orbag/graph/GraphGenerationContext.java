package orbag.graph;

import org.springframework.security.core.Authentication;

import javax.security.auth.Subject;

public class GraphGenerationContext {


    Authentication  user;

    Path path;

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Authentication getUser() {
        return user;
    }

    public void setUser(Authentication user) {
        this.user = user;
    }
}
