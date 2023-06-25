package orbag.graph;


import org.springframework.beans.factory.BeanNameAware;

public abstract class PathBase implements BeanNameAware, Path {

    String identifier;

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public void setBeanName(String name) {
        this.identifier = name;
    }

}

