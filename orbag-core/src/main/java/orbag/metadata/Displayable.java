package orbag.metadata;

public interface Displayable {

	default String getDisplayLabel() {
		DisplayLabel displayLabel = this.getClass().getAnnotation(DisplayLabel.class);
		if (displayLabel!=null) {
			return displayLabel.value();
		}
		return this.toString();
	}
}
