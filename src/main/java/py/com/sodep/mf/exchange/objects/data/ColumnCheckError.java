package py.com.sodep.mf.exchange.objects.data;

/**
 * <p>
 * This is an error that happened during the evaluation of an insert or update
 * to a set of data
 * </p>
 * .
 * <p>
 * There are two types of errors:
 * <ul>
 * <li>DATA_TYPE : the data type of the value doesn't match the data type of the
 * declaration</li>
 * <li>MISSING_FIELD : There is a field that wasn't declared in the DataSet
 * Definition</li>
 * </ul>
 * </p>
 * 
 * @author danicricco
 * 
 */
public class ColumnCheckError {

	public static enum TYPE {
		DATA_TYPE, MISSING_FIELD, WRONG_BLOB, WRONG_LOCATION
	};

	private TYPE errorType;
	private String offendingField;
	private Object value;

	public ColumnCheckError() {

	}

	public ColumnCheckError(TYPE errorType, String offendingField, Object value) {

		this.errorType = errorType;
		this.offendingField = offendingField;
		this.value = value;
	}

	public ColumnCheckError(TYPE errorType, String offendingField) {
		this(errorType, offendingField, null);
	}

	public TYPE getErrorType() {
		return errorType;
	}

	/**
	 * 
	 * @return The field that didn't pass the check
	 */
	public String getOffendingField() {
		return offendingField;
	}

	public Object getValue() {
		return value;
	}

	public void setErrorType(TYPE errorType) {
		this.errorType = errorType;
	}

	public void setOffendingField(String offendingField) {
		this.offendingField = offendingField;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ColumnCheckError [errorType=" + errorType + ", offendingField=" + offendingField + ", value=" + value
				+ "]";
	}

}