package orbag.visibility;

public interface ObjectVisibilityFilter {

	boolean isActiveForObject(Object objectToTest, FilterContext request);
	
	boolean isObjectVisibile(Object objectToTest, FilterContext request);
	
}
