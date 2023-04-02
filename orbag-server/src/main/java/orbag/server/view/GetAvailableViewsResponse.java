package orbag.server.view;

import java.util.List;

public class GetAvailableViewsResponse {

	List<SerializableView> availableViews;

	public List<SerializableView> getAvailableViews() {
		return availableViews;
	}

	public void setAvailableViews(List<SerializableView> availableViews) {
		this.availableViews = availableViews;
	}
}
