package orbag.graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PathRegistry {

    @Autowired(required = false)
    List<Path> views;

    public List<Path> getAllPaths() {
        return views;
    }
}
