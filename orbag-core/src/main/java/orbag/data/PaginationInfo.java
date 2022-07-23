package orbag.data;

public class PaginationInfo {

	Integer size= Integer.MAX_VALUE;

	Integer offset=0;

	public PaginationInfo() {
	}

	public PaginationInfo(Integer size) {
		this.size = size;
	}

	public PaginationInfo(Integer size, Integer offSet) {
		this.size = size;
		this.offset = offSet;
	}

	public Integer getSize() {
		return size;
	}

	public Integer getOffset() {
		return offset;
	}
	
	
}
