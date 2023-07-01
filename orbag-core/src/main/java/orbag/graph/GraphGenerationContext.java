package orbag.graph;

import org.springframework.security.core.Authentication;

import javax.security.auth.Subject;
import java.util.List;
import java.util.function.Predicate;

public class GraphGenerationContext {


    Authentication  user;

    Path path;

    List<?> previousSteps;

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

    public List<?> getPreviousSteps() {
        return previousSteps;
    }

    public void setPreviousSteps(List<?> previousSteps) {
        this.previousSteps = previousSteps;
    }

    public boolean filterByPreviousSteps(Predicate<Object>... filters) {
        if (previousSteps==null) {
            return false;
        }
        if (previousSteps.size() < filters.length) {
            return false;
        }
        for (int cnt = 0;cnt < filters.length;cnt++) {
            if (! filters[cnt].test(previousSteps.get(cnt))) {
                return false;
            }
        }
        return  true;
    }
}
