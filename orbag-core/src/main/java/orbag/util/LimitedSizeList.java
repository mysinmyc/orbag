package orbag.util;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class LimitedSizeList<T> extends ArrayList<T> {

	private Integer limit = Integer.MAX_VALUE ;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public boolean add(T e) {
		if (size() >= limit) {
			throw new LimitExceededException();
		}
		return super.add(e);
	}
}
