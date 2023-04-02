package orbag.metadata;

public interface Displayable {

	default String getDisplayLabel() {
		return DisplayLabelUtils.getDefaultDisplayLabel(this);
	}
}
