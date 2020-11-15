package py.com.sodep.mf.exchange.objects.data;

import java.util.List;
import java.util.Map;

public class DocumentSaveResponse {

	private boolean success = false;

	private long numberOfAffectedRows;

	private List<Integer> errorIndex;

	private Map<Integer, String> errorMessages;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getNumberOfAffectedRows() {
		return numberOfAffectedRows;
	}

	public void setNumberOfAffectedRows(long numberOfAffectedRows) {
		this.numberOfAffectedRows = numberOfAffectedRows;
	}

	public List<Integer> getErrorIndex() {
		return errorIndex;
	}

	public void setErrorIndex(List<Integer> errorIndex) {
		this.errorIndex = errorIndex;
	}

	public Map<Integer, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<Integer, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	@Deprecated //For backwards compatibility
	public Boolean getResult(){
		return success;
	}
	
	@Deprecated //For backwards compatibility
	public void setResult(Boolean result){
		this.success = result != null ? result : false;
	}

}
