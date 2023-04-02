package orbag.search;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial")
public class SearchConditions extends ArrayList<SearchCondition<?>> {

	boolean allRequired =true;
	
	protected SearchConditions(SearchCondition<?>... conditions) {
		this(true,conditions);
	}
	
	protected SearchConditions(boolean allRequired, SearchCondition<?>... conditions) {
		this.allRequired = allRequired;
		this.addAll(Arrays.asList(conditions));
	}
		
	public boolean isAllRequired() {
		return allRequired;
	}

	public <T> SearchConditions withCondition(String field, Operator operator, T value) {
		add(new SearchCondition<T>(field, operator, value));
		return this;
	}
	
	public static SearchConditions and(SearchCondition<?>... conditions) {
		return new SearchConditions(true,conditions);
	}
	
	public static SearchConditions or(SearchCondition<?>... conditions) {
		return new SearchConditions(false,conditions);
	}
}
