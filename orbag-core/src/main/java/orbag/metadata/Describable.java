package orbag.metadata;

public interface Describable {

    default String getDescription() {
        Description description=this.getClass().getAnnotation(Description.class);
        if (description==null) {
            return null;
        } else {
            return description.value();
        }
    }
}
