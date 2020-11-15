package py.com.sodep.mf.exchange;

import java.util.List;

/**
 * On the project API is an exactly same class. This is a duplicate for the REST
 * project because the name of the class is included on the custom header
 * attribute x-sodep-mf-class
 * 
 * @author danicricco
 * 
 * @param <T>
 */
public final class PagedData<T extends List<?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T data;

	private Long totalCount = 0L;

	private Integer pageNumber;

	private Integer pageSize = 0;

	private Integer available;

	public PagedData() {

	}

	public PagedData(T data) {
		this.data = data;
	}

	public PagedData(T data, Long totalCount, Integer pageNumber, Integer pageSize, Integer available) {
		this.data = data;
		this.totalCount = totalCount;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.available = available;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPages() {
		double count = totalCount.doubleValue();
		double size = pageSize.doubleValue();
		if (size > 0) {
			Long total = Long.valueOf((long) Math.ceil(count / size));
			return total;
		}
		return 0L;
	}

	public void setTotalPages(Long l) {
		// do nothing since this value is computed in runtime. We need this
		// property in order to deserialize with Jackson
		
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}
