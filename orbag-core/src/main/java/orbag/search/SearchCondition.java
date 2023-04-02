package orbag.search;

public class SearchCondition<T> {

	public static String WILDCARD="%";
	
	String field;
	Operator operator;
	T value;
	
	public SearchCondition(String field, Operator operator, T value) {
		this.field= field;
		this.operator = operator;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public Operator getOperator() {
		return operator;
	}

	public T getValue() {
		return value;
	}

}
