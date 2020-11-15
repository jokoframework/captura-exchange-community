package py.com.sodep.mf.exchange.objects.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class represents the errors that happened in a given row. If the method
 * {@link #getNumberOfErrors()} return zero, then there wasn't any error on the
 * row.
 * 
 * @author danicricco
 * 
 */
public class RowCheckError {

	private List<ColumnCheckError> columnErrors;
	private Object handle;

	public RowCheckError() {

	}

	public RowCheckError(Object handle, List<ColumnCheckError> columnErrors) {
		this.handle = handle;
		this.columnErrors = columnErrors;
	}

	public List<ColumnCheckError> getColumnErrors() {
		return columnErrors;
	}

	@JsonIgnore
	public Integer getNumberOfErrors() {
		if (columnErrors != null) {
			return columnErrors.size();
		}
		return 0;
	}

	public Object getHandle() {
		return handle;
	}

	public void setColumnErrors(List<ColumnCheckError> columnErrors) {
		this.columnErrors = columnErrors;
	}

	public void setHandle(Object handle) {
		this.handle = handle;
	}

	@Override
	public String toString() {
		return "RowCheckError [columnErrors=" + columnErrors + ", handle=" + handle + "]";
	}

}
