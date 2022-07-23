package orbag.visibility;

public interface ClassVisibilityFilter {
	boolean isActiveForClass(Class<?> classToTest, FilterContext context );
	boolean isClassVisibile(Class<?> classToTest, FilterContext context );
}
