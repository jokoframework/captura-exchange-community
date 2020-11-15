package py.com.sodep.mf.exchange.objects.data;

import java.util.List;

/**
 * This is the summary after performing a DML operation using the
 * {@link DataAccessService}
 * 
 * @author danicricco
 * 
 */
public class MFOperationResult {

	public static enum RESULT {
		SUCCESS, FAIL, TIMEOUT
	};

	private RESULT result;
	private int numberOfAffectedRows;
	private List<RowCheckError> errors;

	private String msg;

	public MFOperationResult() {

	}

	public MFOperationResult(int numberOfAffectedRows) {
		result = RESULT.SUCCESS;
		this.numberOfAffectedRows = numberOfAffectedRows;
	}

	public MFOperationResult(List<RowCheckError> errors) {
		result = RESULT.FAIL;
		this.errors = errors;
	}

	public MFOperationResult(RESULT result) {
		this.result = result;
	}

	public int getNumberOfAffectedRows() {
		return numberOfAffectedRows;
	}

	public void setNumberOfAffectedRows(int numberOfAffectedRows) {
		this.numberOfAffectedRows = numberOfAffectedRows;
	}

	public List<RowCheckError> getErrors() {
		return errors;
	}

	public void setErrors(List<RowCheckError> errors) {
		this.errors = errors;
	}

	public boolean hasFailed() {
		if (result.equals(RESULT.FAIL)) {
			return true;
		}
		return false;
	}

	public boolean hasSucceeded() {
		if (result.equals(RESULT.SUCCESS)) {
			return true;
		}
		return false;
	}

	public boolean hasTimedOut() {
		if (result.equals(RESULT.TIMEOUT)) {
			return true;
		}
		return false;
	}

	public RESULT getResult() {
		return result;
	}

	public void setResult(RESULT result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
